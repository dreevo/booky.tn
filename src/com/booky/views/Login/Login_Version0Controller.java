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
        if (mail.getText().isEmpty()==false && password.getText().isEmpty() == false) {
        	validateLogin();
         }else 
        	 System.out.println("check ur data please!");
    }
     public void validateLogin() throws IOException {
    	/*Connection cnx = DataSource.getInstance().getCnx();
    	String verifyLogin = "SELECT COUNT(1) FROM customer WHERE email = '"+mail.getText()+"' AND password = '"+password.getText()+"' "  ;   
    	
    	
    	try {
    		Statement st = cnx.createStatement();			  
			ResultSet res = st.executeQuery(verifyLogin);
			while(res.next()) {
                    if(res.getInt(1)==1) {
                	System.out.println("Connexion is done !");
                	
                	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Account.fxml"));
                         Parent root1 = (Parent) fxmlLoader.load();
                         Stage stage = new Stage();
                         stage.setScene(new Scene(root1));
                          stage.show();
              }else {
                 	System.out.println("connexion is failed");
              }
			}
    	}catch(Exception e){
    		e.printStackTrace();
    		e.getCause();
    		
    	}*/
         if (mail.getText().isEmpty() && password.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an email and password", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            CustomerService cs = new CustomerService();
            if (cs.validateLogin(mail.getText(), password.getText())) {
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
        JavaMailUtil.sendMail("marwa.jebali@esprit.tn");
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
    
}
