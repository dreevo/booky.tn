/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Author;
import com.booky.entities.Blog;
import com.booky.services.AuthorService;
import com.booky.services.BlogService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 *
 *
 * @author yanisInfo
 */
public class AddBlogController implements Initializable {

    @FXML
    private TextArea tastatus;
    @FXML
    private Button btn;
    @FXML
    private TextField tftitle;
    @FXML
    private ComboBox<Author> authorField;
    @FXML
    private Label indexBtn;
    @FXML
    private Label booksWithCharityLabel;
    @FXML
    private Label libraryBtn;
    @FXML
    private Label orderPanelBtn;
    @FXML
    private Label donationsPage;
    @FXML
    private Label profileBtn;
    @FXML
    private Label eventBtn;
    @FXML
    private Label blogBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
//Getting the list of the authors for the author combo box 

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
    }    

    @FXML
    private void AjouterBlog(ActionEvent event) throws IOException {
        boolean inputValid = true;
        
//Retriving the author of the blog
        Author author = authorField.getSelectionModel().getSelectedItem();
        if (author == null) {
            JOptionPane.showMessageDialog(null, "Please choose an author", "Error", JOptionPane.ERROR_MESSAGE);
            inputValid = false;
            return;
        } else {
            System.out.println("Author's id " + author.getId());
        }
        BlogService bs = new BlogService();
        Blog b;
        b = new Blog(tftitle.getText(),tastatus.getText(),author);
        bs.createBlog(b);
        JOptionPane.showMessageDialog(null,"Blog Added");
      
        
   }

    @FXML
    private void cancelRegister(ActionEvent event) {
        try {
            Stage stage1 = (Stage) booksWithCharityLabel.getScene().getWindow();
            stage1.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ReadBlogs.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Manage Events");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
