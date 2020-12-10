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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class ParticipateInEventController implements Initializable {

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
    private TextField customerIdField;
    @FXML
    private Button addevent;
    @FXML
    private ComboBox<Event> eventfield;
    ObservableList<Event> eventList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EventService es = new EventService();
        es.readEvents(eventList);
        for (int i = 0; i < eventList.size(); i++) {
            eventfield.getItems().add(eventList.get(i));
        }
        eventfield.setConverter(new StringConverter<Event>() {
            @Override
            public String toString(Event object) {
                return (object.getTitle());
            }

            @Override
            public Event fromString(String string) {
                return eventfield.getItems().stream().filter(ap
                        -> ap.getTitle().equals(string)).findFirst().orElse(null);
            }
        });
        System.out.println(eventList);

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

    @FXML
    private void AddEvent(ActionEvent event) {

        Event ev = eventfield.getSelectionModel().getSelectedItem();
        if (ev == null) {
            JOptionPane.showMessageDialog(null, "Please choose an event", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int eventId = ev.getId();
            EventService es = new EventService();
            es.addCustomerInEvent(Integer.parseInt(customerIdField.getText()), eventId);
            JOptionPane.showMessageDialog(null, "Customer add to event", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }

}
