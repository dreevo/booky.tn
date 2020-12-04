/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Customer;
import com.booky.services.CustomerService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class CreateCustomerController implements Initializable {
    @FXML
    private TextField tfFirstname;
    @FXML
    private TextField tfLastname;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfTelephone;
    @FXML
    private TextField tfImage;
    @FXML
    private Button createBtn;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createCustomer(ActionEvent event) {
        CustomerService cs = new CustomerService();
        Customer c = new Customer(tfFirstname.getText(), tfLastname.getText(),Integer.parseInt(tfAge.getText()),tfEmail.getText(),tfAddress.getText(),tfTelephone.getText(),tfImage.getText(),null);
        cs.addCustomer(c);
    }
    
}
