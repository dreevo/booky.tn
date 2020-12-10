/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.services.CustomerService;
import com.booky.utils.MailSender;
import java.net.URL;
import java.util.Random;
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
 * @author gharbimedaziz
 */
public class MailController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private Button sendbtn;
    @FXML
    private Button cancelBtn;
    private static String code = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void tfxsendbtn(ActionEvent event)throws Exception {
        this.code = randomcode();
        int count = 0;
        CustomerService cs = new CustomerService();
        count = cs.validateEmail(mail.getText());
        if (count != 0) {
            MailSender.sendMail(mail.getText(), this.code);
            System.out.println(this.code);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("verification.fxml"));
            Parent root = loader.load();
            VerificationController vc = loader.getController();
            vc.setCode(this.code);
            Scene scene = new Scene(root);
            Stage registerStage = new Stage();
            registerStage.setTitle("Code verif");
            registerStage.setScene(scene);
            registerStage.show();
        } else {
            JOptionPane.showMessageDialog(null, "This email doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void cancelVerif(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    public static String randomcode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
    
    
}

