/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.services.CustomerService;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginButtonClick(ActionEvent event) {
        if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an email and password", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            CustomerService cs = new CustomerService();
            if (cs.validateLogin(usernameField.getText(), passwordField.getText())) {
                JOptionPane.showMessageDialog(null, "User Authenticated", "Success", JOptionPane.OK_CANCEL_OPTION);
            } else {
                JOptionPane.showMessageDialog(null, "Email or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    private void registerBtnClick(ActionEvent event) {
        Stage stage = (Stage) registerBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage registerStage = new Stage();
            registerStage.setTitle("Register");
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
