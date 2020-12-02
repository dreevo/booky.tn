/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Author;
import com.booky.entities.Language;
import com.booky.services.AuthorService;
import com.booky.services.LanguageService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

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
    private Button createBtn;
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
                        -> ap.getFirstName().equals(string)).findFirst().orElse(null);
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
    }

    @FXML
    private void cancelCreate(ActionEvent event) {
    }

    @FXML
    private void createBook(ActionEvent event) {
    }

    @FXML
    private void selectImage(ActionEvent event) {
    }

}
