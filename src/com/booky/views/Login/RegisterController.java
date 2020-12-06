/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views.Login;

import com.booky.entities.Author;
import com.booky.entities.Customer;
import com.booky.entities.Role;
import com.booky.services.CustomerService;
import com.booky.utils.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author J.Maroua
 */
public class RegisterController  implements Initializable {

    @FXML
    private TextField firstname;
    @FXML
    private TextField age;
    @FXML
    private Button reset;
    @FXML
    private Button sumbit;
    @FXML
    private Button back;
    @FXML
    private TextField address;
    @FXML
    private TextField mail;
    @FXML
    private TextField phone;
    @FXML
    private TextField lastname;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmpassword;
    @FXML
    private Button imageUrl;
    private ArrayList<String> lstFile;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        age.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    age.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        ArrayList<String> lstFile = null;
        lstFile.add(".jpg");
        lstFile.add(".jpeg");
        lstFile.add("*.png");
    }    

    @FXML
    private void resetbtn(ActionEvent event) {
        firstname.setText("");
        lastname.setText("");
        mail.setText("");
        age.setText("");
        password.setText("");
        confirmpassword.setText("");
    }

    @FXML
    private void signupbtn(ActionEvent event) {
      /*  CustomerService bs = new CustomerService();
        Role r1 =new Role(1,"admin");
        bs.addCustomer(new Customer(firstname.getText(), lastname.getText(),Integer.parseInt(age.getText()), mail.getText(), password.getText(), address.getText(), phone.getText(), password.getText(),r1));
        JOptionPane.showMessageDialog(null, "Account created with succes");*/
       
  if (firstname.getText().isEmpty() || lastname.getText().isEmpty() || mail.getText().isEmpty() || password.getText().isEmpty() || confirmpassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the required fields", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else if (!password.getText().equals(confirmpassword.getText())) {
            passwordMatch.setText("Passwords do not match");
        } else {
            passwordMatch.setText("");
            int age = 0;
            if (!age.getText().isEmpty()) {
                age = Integer.parseInt(age.getText());
                if (age < 10 || age > 120) {
                    ageValid.setText("Invalid age value");
                }
            }
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(mail.getText());
            if (!matcher.matches()) {
                emailValid.setText("Invalid email");
            } else {
                Customer c = new Customer(firstname.getText(), lastname.getText(), age, email.getText(), address.getText(), phone.getText(), imageUrl.getText(), password.getText(), new Role(2, "Customer"));
                CustomerService cs = new CustomerService();
                cs.addCustomer(c);
                JOptionPane.showMessageDialog(null, "User Registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                Stage stage = (Stage) signupbtn.getScene().getWindow();
                // do what you have to do
                stage.close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login_Version0.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage registerStage = new Stage();
                    registerStage.setTitle("Login");
                    registerStage.setScene(scene);
                    registerStage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @FXML
    private void backbtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login_Version0.fxml"));
                             Parent root1 = (Parent) fxmlLoader.load();
                              Stage stage = new Stage();
                              stage.setScene(new Scene(root1));
                               stage.show();
    }

    @FXML
    private void uploadimg(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", lstFile));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            imgLabel.setText(f.getName());
        }
    }
    
}
