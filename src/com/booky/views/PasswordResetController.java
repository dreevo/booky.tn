/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Customer;
import com.booky.services.CustomerService;
import com.booky.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
 * @author gharbimedaziz
 */
public class PasswordResetController implements Initializable {

    @FXML
    private Button changebtn;
    @FXML
    private PasswordField password;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void tfxchangebtn(ActionEvent event) throws IOException {
        Connection cnx = DataSource.getInstance().getCnx();
        CustomerService bs = new CustomerService();
        bs.updateCustomer(new Customer(password.getText(), "text"), LoginController.id);
        JOptionPane.showMessageDialog(null, "Pwd changed with succes");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login_Version0.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void cancelVerif(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
