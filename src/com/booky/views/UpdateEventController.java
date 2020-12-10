/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Event;
import com.booky.services.EventService;
import static com.booky.views.LoginController.id;
import java.io.IOException;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class UpdateEventController implements Initializable {

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
    private Button UpdateEvent1;
    @FXML
    private Button UpdateEvent;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void updateEvent(ActionEvent event) {
        if (title.getText().isEmpty() || description.getText().isEmpty() || begindate.getText().isEmpty() || enddate.getText().isEmpty() || imageUrl.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the required fields", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        EventService es = new EventService();
        Event ev = new Event();
        ev.setTitle(title.getText());
        ev.setDescription(description.getText());
        ev.setBeginDate(begindate.getText());
        ev.setEndDate(enddate.getText());
        ev.setImageUrl(imageUrl.getText());
        es.updateEvent(ev);
        JOptionPane.showMessageDialog(null, "event updated");
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        EventService es = new EventService();
        es.deleteEvent(title.getText());
        Event ev = new Event(title.getText(), description.getText(), begindate.getText(), enddate.getText(), imageUrl.getText());
        String title = ev.getTitle();
        if (title == null) {
            JOptionPane.showMessageDialog(null, "Event not found", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            es.deleteEvent(title);
            JOptionPane.showMessageDialog(null, "Event deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void close(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) close.getScene().getWindow();
        // do what you have to do
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReadEvents.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
