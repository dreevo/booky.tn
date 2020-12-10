/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Customer;
import com.booky.entities.Role;
import com.booky.services.CustomerService;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author J.Maroua
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField ageField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmpassField;
    @FXML
    private Button registerBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label passwordMatch;
    @FXML
    private TextField teleField;
    @FXML
    private TextField addressField;
    @FXML
    private Label imgLabel;
    private ArrayList<String> lstFile;
    @FXML
    private Label emailValid;
    @FXML
    private Label ageValid;
    private static final String regex = "^(.+)@(.+)$";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ageField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    ageField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        lstFile = new ArrayList<>();
        lstFile.add("*.jpg");
        lstFile.add("*.jpeg");
        lstFile.add("*.png");
    }

    @FXML
    private void validateRegister(ActionEvent event) throws Exception {
        //int count = 0;
     //  CustomerService CS = new CustomerService();
       //  count = CS.validateEmail(mail.getText());
        if (firstnameField.getText().isEmpty() || lastnameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty() || confirmpassField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the required fields", "Error", JOptionPane.INFORMATION_MESSAGE);
//        } else   if (count == 0){
//           JOptionPane.showMessageDialog(null, "connect directly", "This email already exist !", JOptionPane.INFORMATION_MESSAGE);
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login_Version0.fxml"));
//                         Parent root1 = (Parent) fxmlLoader.load();
//                         Stage stage = new Stage();
//                         
//                         stage.setScene(new Scene(root1));
//                          stage.show();
//                          
            
            } else if (!passwordField.getText().equals(confirmpassField.getText())) {
            passwordMatch.setText("Passwords do not match");
        } else {
            passwordMatch.setText("");
            int age = 0;
            if (!ageField.getText().isEmpty()) {
                age = Integer.parseInt(ageField.getText());
                if (age < 10 || age > 120) {
                    ageValid.setText("Invalid age value");
                }
            }
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(emailField.getText());
            if (!matcher.matches()) {
                emailValid.setText("Invalid email");
            } else {
                String passwordh = BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt ());
                System.out.println(passwordh);
                Customer c = new Customer(firstnameField.getText(), lastnameField.getText(), age, emailField.getText(),passwordField.getText(), addressField.getText(), teleField.getText(), imgLabel.getText(), new Role(2, "Customer"));
                CustomerService cs = new CustomerService();
                cs.addCustomer(c);
                JOptionPane.showMessageDialog(null, "User Registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                Stage stage = (Stage) registerBtn.getScene().getWindow();
                // do what you have to do
                stage.close();
                try {
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("Login_Version0.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage registerStage = new Stage();
            registerStage.setTitle("Log In");
            registerStage.setScene(scene);
            registerStage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @FXML
    private void resetFields(ActionEvent event) {
        firstnameField.setText("");
        lastnameField.setText("");
        emailField.setText("");
        ageField.setText("");
        passwordField.setText("");
        confirmpassField.setText("");
    }

    @FXML
    private void cancelRegister(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
        Platform.exit();
    }

    @FXML
    private void selectImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", lstFile));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            imgLabel.setText(f.getName());
        }
    }

}
