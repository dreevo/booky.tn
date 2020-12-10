/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views.Login;

import com.booky.services.CustomerService;
import com.booky.utils.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author J.Maroua
 */
public class Login_Version0Controller implements Initializable {

    
    @FXML
    private TextField password;
    @FXML
    private Button cancel;
    @FXML
    private Button signin;
    @FXML
    private CheckBox remember;
    @FXML
    private Hyperlink forget;
    @FXML
    private Button signup;
    @FXML
    private TextField mail;
    public static int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelbtn(ActionEvent event) {
    }

    @FXML
    private void signinbtn(ActionEvent event) throws IOException {
        int count = 0;
        CustomerService cs = new CustomerService();
         count = cs.validateEmail(mail.getText());
        
        if(mail.getText().isEmpty()==false && password.getText().isEmpty() == false) {
        	validateLogin();
         } else
        	 System.out.println("check ur data please!");
        if (count == 0){
         JOptionPane.showMessageDialog(null, "Please create an account", "This email doesn't exist :(", JOptionPane.INFORMATION_MESSAGE); 
    }
    }
     public void validateLogin() throws IOException {
    	
         if (mail.getText().isEmpty() && password.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an email and password", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
             
            CustomerService cs = new CustomerService();
            
            if (cs.authentification(mail.getText(),password.getText())) {
               id=cs.idlogin(mail.getText(),password.getText());
                JOptionPane.showMessageDialog(null, "User Authenticated", "Success", JOptionPane.OK_CANCEL_OPTION);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Account.fxml"));
                         Parent root1 = (Parent) fxmlLoader.load();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(root1));
                          stage.show();
                          id=cs.idlogin(mail.getText(),password.getText());
                         
            } else {
                JOptionPane.showMessageDialog(null, "Email or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    private void rememberbtn(ActionEvent event) {
    }

    @FXML
    private void forgetbtn(ActionEvent event) throws Exception {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Mail.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage registerStage = new Stage();
            registerStage.setTitle("Password forgeted");
            registerStage.setScene(scene);
            registerStage.show();
       
    }

    @FXML
    private void signupbtn(ActionEvent event)  {
     
    Stage stage = (Stage) signup.getScene().getWindow();
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
    
//     public void validateLogin() throws IOException {
//        if (mail.getText().isEmpty() && password.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Please enter an email and password", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            CustomerService cs = new CustomerService();
//            int customerId = cs.validateLogin(mail.getText(), password.getText());
//            if (customerId != 0)&& (cs.authentification(mail.getText(),password.getText())) {
 //              id=cs.idlogin(mail.getText(),password.getText());
//                System.out.println(customerId);
//                JOptionPane.showMessageDialog(null, "User Authenticated", "Success", JOptionPane.OK_CANCEL_OPTION);
//                int roleId = cs.readCustmerRole(customerId);
//                System.out.println(roleId);
//                if (roleId == 1) {
//                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateAccount.fxml"));
//                     Parent root1 = (Parent) fxmlLoader.load();
//                       Stage stage = new Stage();
//                       stage.setScene(new Scene(root1));
//                        stage.show();
 //                  id=cs.idlogin(mail.getText(),password.getText());
//                }else{
//                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account.fxml"));
//                    Parent root1 = (Parent) fxmlLoader.load();
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(root1));
//                    stage.show();
    //             id=cs.idlogin(mail.getText(),password.getText());
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Email or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
}
