/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Blog;
import com.booky.entities.Comment;
import com.booky.entities.Customer;
import com.booky.services.BlogService;
import com.booky.services.CommentService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author yanisInfo
 */
public class ReadBlogsController implements Initializable {

    @FXML
    private TableView<Blog> table;

    @FXML
    private TableColumn<Blog, String> blogtitle;

    /*@FXML
    private Button btn;
     */
    ObservableList<Blog> blogList = FXCollections.observableArrayList();
    @FXML
    private Label contentField;
    @FXML
    private Label authorField;
    @FXML
    private TilePane tilePane;
    private int blogId = 0;
    @FXML
    private TextField commentField;
    @FXML
    private Button reloadBtn;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BlogService bs = new BlogService();
        bs.readBlogs(blogList);
        blogtitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        System.out.println(blogList);
        table.setItems(blogList);
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> loadPage(newValue));
        indexBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
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
        });
    }

    public void loadPage(Blog b) {
        tilePane.getChildren().clear();
        contentField.setText(b.getContent());
        authorField.setText(b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName());
        blogId = b.getId();
        VBox commentBox = new VBox();
        for (int i = 0; i < b.getComments().size(); i++) {
            HBox commentLine = new HBox();
            Label customerLabel = new Label();
            customerLabel.setStyle("-fx-text-fill:white;");
            customerLabel.setText(b.getComments().get(i).getCustomer().getFirstName() + " " + b.getComments().get(i).getCustomer().getLastName() + " : ");
            Label commentLabel = new Label();
            commentLabel.setText(b.getComments().get(i).getDescription());
            commentLabel.setStyle("-fx-text-fill:white;");
            commentLine.getChildren().add(customerLabel);
            commentLine.getChildren().add(commentLabel);
            commentBox.getChildren().add(commentLine);
        }
        tilePane.getChildren().add(commentBox);
    }

    @FXML
    private void updateBlog(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) reloadBtn.getScene().getWindow();
        // do what you have to do
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateBlog.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update Blogs");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root2));
        stage.show();

    }

    @FXML
    private void createBlog(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) reloadBtn.getScene().getWindow();
        // do what you have to do
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddBlog.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Add new Blogs");
        stage.setScene(new Scene(root2));
        stage.show();
    }

    @FXML
    private void deleteBlog(ActionEvent event) throws IOException {
        if(blogId== 0){
            JOptionPane.showMessageDialog(null, "Please choose a blog", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
         BlogService bs =new BlogService();
         bs.deleteBlog(blogId);
         JOptionPane.showMessageDialog(null,"Blog Deleted");
        }
    }

    @FXML
    private void postComment(ActionEvent event) {
        if (blogId == 0) {
            JOptionPane.showMessageDialog(null, "Please choose a blog to delete", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Comment c = new Comment();
            c.setDescription(commentField.getText());
            c.setCustomer(new Customer(1));
            c.setBlog(new Blog(blogId));
            CommentService cs = new CommentService();
            cs.createComment(c);
        }
    }

    @FXML
    private void reloadPage(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) reloadBtn.getScene().getWindow();
        // do what you have to do
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ReadBlogs.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Read Blogs");
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
