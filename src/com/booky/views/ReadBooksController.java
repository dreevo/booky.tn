/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Book;
import com.booky.services.BookService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class ReadBooksController implements Initializable {

    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, String> label;

    /**
     * Initializes the controller class.
     */
    ObservableList<Book> bookList = FXCollections.observableArrayList();
    @FXML
    private Label labelField;
    @FXML
    private Label priceField;
    @FXML
    private Label descField;
    @FXML
    private Label stockField;
    @FXML
    private Label ratingField;
    @FXML
    private Label langField;
    @FXML
    private Label authField;
    @FXML
    private Label imgField;
    @FXML
    private Label categField;
    @FXML
    private Button editBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button cancelBtn;
    private int bookId;
    @FXML
    private Button reloadBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        BookService bs = new BookService();
        bs.readBooks(bookList);
        label.setCellValueFactory(new PropertyValueFactory<>("label"));
        table.setItems(bookList);
        // Clear person details
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    private void showPersonDetails(Book b) {
        if (b != null) {
            categField.setText("");
            // Fill the labels with info from the person object.
            labelField.setText(b.getLabel());
            priceField.setText("" + b.getPrice() + " DT");
            descField.setText(b.getDescription());
            if (b.getIsInStock() == 1) {
                stockField.setText("Yes");
            } else {
                stockField.setText("No");
            }
            ratingField.setText("" + b.getRating());
            langField.setText(b.getLanguage().getLanguageName());
            imgField.setText(b.getImageUrl());
            authField.setText(b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName());
            bookId = b.getId();
            for (int i = 0; i < b.getCategories().size(); i++) {
                if (i == 0) {
                    categField.setText(b.getCategories().get(i).getCategoryName());
                } else {
                    categField.setText(categField.getText() + " , " + b.getCategories().get(i).getCategoryName());
                }
            }
        } else {
            // Person is null, remove all the text.
            labelField.setText("");
        }
    }

    @FXML
    private void editBook(ActionEvent event) throws IOException {
        if (bookId == 0) {
            JOptionPane.showMessageDialog(null, "Please select a book to edit", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/UpdateBook.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            UpdateBookController controller = fxmlLoader.<UpdateBookController>getController();
            controller.setUser(bookId);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Edit Book");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    @FXML
    private void deleteBook(ActionEvent event) {
        int id = bookId;
        BookService bs = new BookService();
        if (id == 0) {
            JOptionPane.showMessageDialog(null, "Please select a book to delete", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            bs.deleteBook(bookId);
            JOptionPane.showMessageDialog(null, "Book deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void cancelBook(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void reloadTable(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadBooks.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage registerStage = new Stage();
            registerStage.setTitle("Manage Books");
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
