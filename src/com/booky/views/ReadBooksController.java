/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Book;
import com.booky.entities.Author;
import com.booky.services.BookService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    @FXML
    private Button addBookBtn;
    @FXML
    private TextField filterfield;
    @FXML
    private Text searchbox;
    @FXML
    private Label bookdetailslabel;
    @FXML
    private Label librarybtn;
    @FXML
    private Label indexBtn;
    @FXML
    private Label booksWithCharityLabel;
    @FXML
    private Label orderPanelBtn;
    @FXML
    private Label donationsPage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //bookdetailslabel.setVisible(false);
        // TODO
        BookService bs = new BookService();
        bs.readBooks(bookList);
        label.setCellValueFactory(new PropertyValueFactory<>("label"));
        table.setItems(bookList);
        showBookDetails(null);

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookDetails(newValue));
        FilteredList<Book> filteredData = new FilteredList<>(bookList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Book -> {
                // If filter text is empty, display all books.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (Book.getLabel().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (Book.getAuthor().getFirstName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (Book.getAuthor().getLastName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if ((Book.getAuthor().getLastName() + " " + Book.getAuthor().getFirstName()).toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if ((Book.getAuthor().getFirstName() + " " + Book.getAuthor().getLastName()).toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (Book.getCategory().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Book> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    private void showBookDetails(Book b) {
        if (b != null) {
            categField.setText("");
            // Fill the labels with info from the book object.
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
            // Book is null, remove all the text.
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
            stage.initStyle(StageStyle.TRANSPARENT);
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
            send("bookycorp.tn@gmail.com", "booky123456789", "maha.bacha2806@gmail.com", "AUTOMATIC MESSAGE from booky.tn a BOOK was deleted", "The book " + labelField.getText() + " was deleted from library.");
        }
    }

    @FXML
    private void cancelBook(ActionEvent event) {
        try {
            Stage stage1 = (Stage) indexBtn.getScene().getWindow();
            stage1.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Index.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Manage Orders");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
            registerStage.initStyle(StageStyle.TRANSPARENT);
            registerStage.setTitle("Manage Books");
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void addBook(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/CreateBook.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Create Book");
        stage.setScene(new Scene(root1));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

    public void send(String from, String password, String to, String sub, String msg) {
        //Get properties object    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message  
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
