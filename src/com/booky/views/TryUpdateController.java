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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class TryUpdateController implements Initializable {

    @FXML
    private TextField tftitle;
    @FXML
    private TextArea tfcontent;
    @FXML
    private Button btn;
    @FXML
    private TableView<Blog> table;
    @FXML
    private TableColumn<Blog, String> titlefiled;
    
    
    ObservableList<Blog> blogList = FXCollections.observableArrayList();
    private int blogId=0;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        BlogService bc = new BlogService();
        bc.readBlogs(blogList);
        titlefiled.setCellValueFactory(new PropertyValueFactory<>("title"));
        System.out.println(blogList);
        table.setItems(blogList);
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> loadPage(newValue));
        
        // TODO
    }    
    public void loadPage(Blog b){
    
       blogId=b.getId();
       
    }

    @FXML
    private void UpdateBlog(ActionEvent event) {
         if(blogId== 0){
            JOptionPane.showMessageDialog(null, "Please choose a blog", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        BlogService bs = new BlogService();
        Blog b = new Blog();
        b.setId(blogId);
        b.setTitle(tftitle.getText());
        b.setContent(tfcontent.getText());
        bs.updateBlog(b);
        JOptionPane.showMessageDialog(null,"Blog Updated");
    }
        
    }
}
