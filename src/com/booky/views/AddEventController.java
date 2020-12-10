/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Event;
import com.booky.services.EventService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class AddEventController implements Initializable {

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
    private Label libraryBtn1;
    @FXML
    private Button close;
    @FXML
    private TextField title;
    @FXML
    private TextField imageUrl;
    @FXML
    private TextField enddate;
    @FXML
    private TextField description;
    @FXML
    private TextField begindate;
    @FXML
    private Button addevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddEvent(ActionEvent event) {
        EventService es=new EventService();
        Event ev=new Event();
        ev.setTitle(title.getText());
        ev.setDescription(description.getText());
        ev.setImageUrl(imageUrl.getText());
        ev.setBeginDate(begindate.getText());
        ev.setEndDate(enddate.getText());
        System.out.println(ev);
        es.createEvent(ev);
        JOptionPane.showMessageDialog(null,"event added");
    }
    
    @FXML
    private void close(ActionEvent event) {
        Stage stage1 = (Stage) close.getScene().getWindow();
        // do what you have to do
        stage1.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ReadEvents.fxml"));
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
