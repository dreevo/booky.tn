/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Event;
import com.booky.services.EventService;
import java.io.File;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class ReadEventsController implements Initializable {

    @FXML
    private TableView<Event> event;
    @FXML
    private TableColumn<Event, String> title;
    @FXML
    private TableColumn<Event, String> description;
    @FXML
    private TableColumn<Event, String> begindate;
    @FXML
    private TableColumn<Event, String> enddate;
    @FXML
    private TableColumn<Event, String> imageUrl;
    ObservableList<Event> EventList = FXCollections.observableArrayList();
    @FXML
    private Button updateEventsBtn;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventService es = new EventService();
        es.readEvent(EventList);
        for (int i = 0; i < EventList.size(); i++) {
            System.out.println(EventList.get(i));
        }
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        begindate.setCellValueFactory(new PropertyValueFactory<>("beginDate"));
        enddate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        imageUrl.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));
        event.setItems(EventList);
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
     private void Uploadimg(ActionEvent event) {
        FileChooser fc = new FileChooser();
        java.lang.String[] lstFile = null;
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", lstFile));
        File f = fc.showOpenDialog(null);
        if(f != null){
           // singleFileLab.setText(f.getPath());
        }
}

    @FXML
    private void updateEvents(ActionEvent event) throws IOException{
        Stage stage1 = (Stage) updateEventsBtn.getScene().getWindow();
        // do what you have to do
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateEvent.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void participateEvent(ActionEvent event) throws IOException{
        Stage stage1 = (Stage) updateEventsBtn.getScene().getWindow();
        // do what you have to do
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ParticipateInEvent.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void addEvent(ActionEvent event)throws IOException {
        Stage stage1 = (Stage) updateEventsBtn.getScene().getWindow();
        // do what you have to do
        stage1.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
