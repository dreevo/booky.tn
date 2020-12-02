/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Author;
import com.booky.entities.Book;
import com.booky.entities.Category;
import com.booky.entities.Language;
import com.booky.services.AuthorService;
import com.booky.services.BookService;
import com.booky.services.LanguageService;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class UpdateBookController implements Initializable {

    @FXML
    private TextField labelField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea descField;
    @FXML
    private Button cancelBtn;
    @FXML
    private CheckBox inStockField;
    @FXML
    private CheckBox advCategField;
    @FXML
    private CheckBox romCategField;
    @FXML
    private CheckBox sciCategField;
    @FXML
    private Slider ratField;
    @FXML
    private Button singleFileChooser;
    @FXML
    private Label singleFileLab;
    @FXML
    private ComboBox<Author> authorField;
    @FXML
    private ComboBox<Language> langField;
    @FXML
    private CheckBox eduField;
    @FXML
    private CheckBox mysField;
    private int bookId;
    @FXML
    private Button updateBtn;
    private ArrayList<String> lstFile;
    private int authorId;
    private int languageId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // PRICE INPUT VALIDATION
        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    priceField.setText(newValue.replaceAll("[^\\d.]", ""));
                }
            }
        });

        // GETTING THE LIST OF THE AUTHORS FOR THE AUTHOR COMBO BOX
        ObservableList<Author> authorsList = FXCollections.observableArrayList();
        AuthorService as = new AuthorService();
        as.readAuthors(authorsList);
        for (int i = 0; i < authorsList.size(); i++) {
            authorField.getItems().add(authorsList.get(i));
        }
        authorField.setConverter(new StringConverter<Author>() {
            @Override
            public String toString(Author object) {
                return (object.getFirstName() + " " + object.getLastName());
            }

            @Override
            public Author fromString(String string) {
                return authorField.getItems().stream().filter(ap
                        -> ap.getEmail().equals(string)).findFirst().orElse(null);
            }
        });
        // GETTING THE LIST OF THE LANGUAGES FOR THE LANGUAGE COMBO BOX
        ObservableList<Language> languageList = FXCollections.observableArrayList();
        LanguageService ls = new LanguageService();
        ls.readLanguages(languageList);
        for (int i = 0; i < languageList.size(); i++) {
            langField.getItems().add(languageList.get(i));
        }
        langField.setConverter(new StringConverter<Language>() {
            @Override
            public String toString(Language object) {
                return (object.getLanguageName());
            }

            @Override
            public Language fromString(String string) {
                return langField.getItems().stream().filter(ap
                        -> ap.getLanguageName().equals(string)).findFirst().orElse(null);
            }
        });
        // INITIALIZING THE IMAGE FILERTING 
        lstFile = new ArrayList<>();
        lstFile.add("*.jpg");
        lstFile.add("*.jpeg");
        lstFile.add("*.png");
        Platform.runLater(() -> {
            // INITIATING THE BOOK WITH OLD VALLUES
            BookService bs = new BookService();
            Book b = bs.readBook(bookId);
            System.out.println(b);
            authorId = b.getAuthor().getId();
            languageId = b.getLanguage().getId();
            labelField.setText(b.getLabel());
            priceField.setText(b.getPrice() + "");
            if (b.getIsInStock() == 1) {
                inStockField.setSelected(true);
            } else {
                inStockField.setSelected(false);
            }
            ArrayList<Category> categs = b.getCategories();
            for (int i = 0; i < categs.size(); i++) {
                if ("Adventure".equals(categs.get(i).getCategoryName())) {
                    advCategField.setSelected(true);
                }
                if ("Romance".equals(categs.get(i).getCategoryName())) {
                    romCategField.setSelected(true);
                }
                if ("Sci-Fi".equals(categs.get(i).getCategoryName())) {
                    sciCategField.setSelected(true);
                }
                if ("Education".equals(categs.get(i).getCategoryName())) {
                    eduField.setSelected(true);
                }
                if ("Mystery".equals(categs.get(i).getCategoryName())) {
                    mysField.setSelected(true);
                }
            }
            authorField.setPromptText(b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName());
            descField.setText(b.getDescription());
            langField.setPromptText(b.getLanguage().getLanguageName());
            ratField.setValue(b.getRating());
            singleFileLab.setText(b.getImageUrl());
        });

    }

    public void setUser(int bookId) {
        this.bookId = bookId;
    }

    @FXML
    private void cancelCreate(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void selectImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", lstFile));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            singleFileLab.setText(f.getName());
        }
    }

    @FXML
    private void updateBook(ActionEvent event) {
        boolean inputValid = true;
        // CHECKING IF THE LABEL IS NOT NULL
        if (labelField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Choose a label", "Error", JOptionPane.ERROR_MESSAGE);
            inputValid = false;
            return;
        }
        // RETRIVING THE PRICE 
        double price = 0;
        if (priceField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the price", "Error", JOptionPane.ERROR_MESSAGE);
            inputValid = false;
            return;
        } else {
            price = Double.parseDouble(priceField.getText());
        }
        // RETRIEVING IF THE BOOK IS IN STOCK OR NOT
        int isInStock = 0;
        if (inStockField.isSelected()) {
            isInStock = 1;
        }
        // RETRIEVING THE AUTHOR OF THE BOOK
        Author author = authorField.getSelectionModel().getSelectedItem();
        System.out.println(author);
        if (author == null) {
            author= new Author(authorId);
        } else {
            System.out.println("Author's id " + author.getId());
        }
        // RETRIEVING SELECTED CATEGORIES
        ArrayList<Category> categories = new ArrayList<>();
        if (romCategField.isSelected()) {
            categories.add(new Category(1, "Romance"));
        }
        if (advCategField.isSelected()) {
            categories.add(new Category(3, "Adventure"));
        }
        if (sciCategField.isSelected()) {
            categories.add(new Category(2, "Sci-Fi"));
        }
        if (eduField.isSelected()) {
            categories.add(new Category(4, "Education"));
        }
        if (mysField.isSelected()) {
            categories.add(new Category(5, "Mystery"));
        }

        if (categories.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please choose atleast one category", "Error", JOptionPane.ERROR_MESSAGE);
            inputValid = false;
            return;
        } else {
            System.out.println("Categories " + categories);
        }
        // RETRIEVING THE DESCRIPTION
        System.out.println("Description " + descField.getText());
        // RETRIEVING THE LANGUAGE OF THE BOOK
        Language language = langField.getSelectionModel().getSelectedItem();
        if (language == null) {
            language = new Language(languageId);
        } else {
            System.out.println("Language " + language.getId());
        }
        // RETRIEVING THE RATING 
        ratField.valueProperty().addListener((obs, oldval, newVal)
                -> ratField.setValue(Math.round(newVal.doubleValue())));
        int rating = (int) (ratField.getValue());
        System.out.println("Rating " + rating);
        Book b = new Book(bookId, labelField.getText(), price, descField.getText(), isInStock, singleFileLab.getText(), author, categories, language, rating);
        BookService bs = new BookService();
        bs.updateBook(b);
        if (inputValid) {
            JOptionPane.showMessageDialog(null, "Book Updated in Database", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
