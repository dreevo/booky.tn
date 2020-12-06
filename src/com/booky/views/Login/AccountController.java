/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views.Login;

import com.booky.utils.DataSource;
import java.io.IOException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author J.Maroua
 */
public class AccountController implements Initializable {

    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editButttonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Updateaccount.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
          stage.show();
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) {
        Connection cnx = DataSource.getInstance().getCnx();
	    	String req = "DELETE from customer WHERE costomerId=10 ";
	    	try {
	    		Statement st = cnx.createStatement();	  		  
	  		    st.executeUpdate(req);
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login_Version0.fxml"));
                             Parent root1 = (Parent) fxmlLoader.load();
                             Stage stage = new Stage();
                             stage.setScene(new Scene(root1));
                             stage.show();
				
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		e.getCause();		
	    	}
    }
    
}
