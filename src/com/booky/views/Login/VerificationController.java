/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views.Login;

import java.net.URL;
import java.util.ResourceBundle;
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
 * @author J.Maroua
 */
public class VerificationController implements Initializable {

    @FXML
    private Button codebtn;
    private String code = "";
    @FXML
    private TextField codeText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void tfxcodebtn(ActionEvent event) {
        if (this.code.equals(codeText.getText())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("pwd.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage registerStage = new Stage();
                registerStage.setTitle("Reset password");
                registerStage.setScene(scene);
                registerStage.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect verification code", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCode(String x) {
        this.code = x;
    }
}
