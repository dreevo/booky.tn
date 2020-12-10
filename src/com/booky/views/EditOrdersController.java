/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Order;
import com.booky.services.OrderService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class EditOrdersController implements Initializable {

    @FXML
    private TableView<Order> table;
    @FXML
    private TableColumn<Order, String> customerColumn;
    @FXML
    private TableColumn<Order, String> isDoneColumn;
    ObservableList<Order> orderList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Order, String> orderDateColumn;
    @FXML
    private Label indexBtn;
    @FXML
    private ImageView backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OrderService os = new OrderService();
        os.readOrders(orderList);
        isDoneColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("IsDoneMessage"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("CustomerEmail"));
        //orderTotalColumn.setCellValueFactory(new PropertyValueFactory<Order, Double>("CartTotal"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("DateOfOrder"));
        isDoneColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Awaiting", "Done"));
        table.getItems().setAll(orderList);
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
        backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Stage stage1 = (Stage) backBtn.getScene().getWindow();
                    stage1.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Index.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setTitle("booky.tn");
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @FXML
    private void updateOrder(TableColumn.CellEditEvent event) {
        Order orderSelected = table.getSelectionModel().getSelectedItem();
        System.out.println(event.getNewValue().toString());
        orderSelected.setIsDoneMessage(event.getNewValue().toString());
        OrderService os = new OrderService();
        os.updateOrderForAdmin(orderSelected);
    }

}
