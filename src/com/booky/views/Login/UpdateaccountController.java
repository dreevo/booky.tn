/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views.Login;

import com.booky.entities.Customer;
import com.booky.services.CustomerService;
import com.booky.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author J.Maroua
 */
public class UpdateaccountController implements Initializable {

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField age;
    @FXML
    private TextField phone;
    @FXML
    private TextField mail;
    @FXML
    private TextField address;
    @FXML
    private PasswordField password;
    @FXML
    private Button editButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    CustomerService s=new CustomerService();
                    Customer c=s.showcustomer(Login_Version0Controller.id);
                    firstname.setText(c.getFirstName());
                    lastname.setText(c.getLastName());
                    c.getAge();
                    mail.setText(c.getEmail());
                    address.setText(c.getAddress());
                    phone.setText(c.getTelephone());
                    
                    
    }    

    @FXML
    private void editButtonAction(ActionEvent event) {
        Connection cnx = DataSource.getInstance().getCnx();
        CustomerService bs = new CustomerService();
        bs.updateCustomer(new Customer(firstname.getText(),lastname.getText(),Integer.parseInt(age.getText()),phone.getText(), mail.getText(),address.getText(), password.getText(),"text"),Login_Version0Controller.id);
        
        JOptionPane.showMessageDialog(null, "Account modified with succes");
    	
    }
    
}
