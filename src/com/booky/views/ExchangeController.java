/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Customer;
import com.booky.entities.Exchange;
import com.booky.services.ExchangeService;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class ExchangeController implements Initializable {

    @FXML
    private AnchorPane myAnchor;
    @FXML
    private Pane myPane;
    @FXML
    private TableView<Exchange> table;
    @FXML
    private TableColumn<Exchange, String> description;
    @FXML
    private TableColumn<Exchange, String> status;
    @FXML
    private Button creationBtn;
    @FXML
    private Pane Creationpane;
    @FXML
    private TextField idTf;
    @FXML
    private TextField descriptionTf;
    @FXML
    private Label updateMsg;
    ObservableList exchangeList = FXCollections.observableArrayList();
    @FXML
    private Button selectBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button validatebtn;
    @FXML
    private Label requiredFieldLabel;
    @FXML
    private Button reloadBtn;
    
    private Exchange exchange= new Exchange();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateMsg.setVisible(false);
        Creationpane.setVisible(false);
        requiredFieldLabel.setVisible(false);

        ExchangeService es = new ExchangeService();
        table.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> changePage(newValue));
        es.readExchanges(exchangeList);
        System.out.println(exchangeList);
        description.setCellValueFactory(new PropertyValueFactory<>("exchangeDescription"));
        status.setCellValueFactory(new PropertyValueFactory<>("exchangeStatus"));
        table.setItems(exchangeList);
        

    }
    
    public void changePage(Exchange e){
        exchange.setExchangeDescription(e.getExchangeDescription());
        exchange.setExchangeStatus(e.getExchangeStatus());
        exchange.setIdCustomerR(e.getIdCustomerR());
        System.out.println(exchange);
        
    }

    @FXML
    private void goToCreation(ActionEvent event) {
        Creationpane.setVisible(true);
         idTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    idTf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        // description exchange VALIDATION
        descriptionTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\sa-zA-Z")) {
                    descriptionTf.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });
    }

    @FXML
    private void createExchange(ActionEvent event) {
       

        if (idTf.getText().isEmpty() || descriptionTf.getText().isEmpty()) {
            requiredFieldLabel.setVisible(true);
        } else {
            ExchangeService es = new ExchangeService();
            es.createExchange(new Exchange(Integer.parseInt(idTf.getText()), descriptionTf.getText(), false));

        }
        }
     @FXML
    private void reloadTable(ActionEvent event) {
           
        try {
           Stage stage1 = (Stage) reloadBtn.getScene().getWindow();
                    stage1.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Exchange.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setTitle("Exchange");
                    stage.setScene(new Scene(root1,1200,660));
                    stage.show();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

    @FXML
    private void selectExchange(ActionEvent event) {
        ExchangeService es = new ExchangeService();
        Customer cSender = new Customer();
        cSender = es.acceptExchange(exchange);
        if(exchange == null){
            JOptionPane.showMessageDialog(null,"please select an offer","ERROR",JOptionPane.ERROR_MESSAGE);
        }else{
                    es.acceptExchange(exchange);

        }
        
         
        
        send("bookycorp.tn@gmail.com", "booky123456789", "nairimalek10@gmail.com", "AUTOMATIC MESSAGE from booky.tn DO NOT REPLY", "You replid to an exchange offer, this is the email " + cSender.getEmail() + " and the phone number "+ cSender.getTelephone()+" of the person of the offer, hope you reach an agreement and never stop reading books!");
        updateMsg.setVisible(true);
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void send(String from, String password, String to, String sub, String msg) {
        //Get properties object    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message  
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

   
}
