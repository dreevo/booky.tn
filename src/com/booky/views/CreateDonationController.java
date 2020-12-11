/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Book;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import com.booky.entities.Customer;
import com.booky.entities.Charity;
import com.booky.entities.Donation;
import com.booky.services.CharityService;
import com.booky.services.DonationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.CustomerRetrieveParams;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
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
public class CreateDonationController implements Initializable {

    @FXML
    private TextField donationMessage;
    @FXML
    private TextField donationAmount;
    @FXML
    private Button donationBtn;

    @FXML
    private ComboBox<Charity> choisirCharity;
    @FXML
    private TextField cardDetails;
    @FXML
    private Label thankyouMsg;
    @FXML
    private Label requiredFieldLabel;
    @FXML
    private Label indexBtn;
    @FXML
    private Label orderPanelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //HIDING LABEL 
        requiredFieldLabel.setVisible(false);
        requiredFieldLabel.setText("Please fill all the required fields !");
        // GETTING THE LIST OF THE CHARITIES FOR THE CHARITY COMBO BOX
        ObservableList<Charity> charityList = FXCollections.observableArrayList();
        CharityService cs = new CharityService();
        cs.readCharities(charityList);
        for (int i = 0; i < charityList.size(); i++) {
            choisirCharity.getItems().add(charityList.get(i));
        }
        choisirCharity.setConverter(new StringConverter<Charity>() {
            @Override
            public String toString(Charity object) {
                return (object.getTitle() + " ");
            }

            @Override
            public Charity fromString(String string) {
                return choisirCharity.getItems().stream().filter(ap
                        -> ap.getTitle().equals(string)).findFirst().orElse(null);
            }
        });
        donationAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    donationAmount.setText(newValue.replaceAll("[^\\d.]", ""));
                }
            }
        });
        // DONATION MESSAGE VALIDATION
        donationMessage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\sa-zA-Z")) {
                    donationMessage.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
                }
            }
        });
        cardDetails.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    cardDetails.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        indexBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Stage stage1 = (Stage) indexBtn.getScene().getWindow();
                    stage1.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Index.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setTitle("Manage Orders");
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

    }

    @FXML
    private void addDonation(ActionEvent event) {
        Charity charity = choisirCharity.getSelectionModel().getSelectedItem();
        if (donationMessage.getText().isEmpty() || donationAmount.getText().isEmpty() || cardDetails.getText().isEmpty() || charity == null) {
            requiredFieldLabel.setVisible(true);

        } else {
            DonationService ds = new DonationService();
            Customer c = new Customer(1);
            Charity ch = new Charity(1);
            ds.createDonation(new Donation(donationMessage.getText(), Double.parseDouble(donationAmount.getText()), c, ch));
            //STRIPE
            Stripe.apiKey = "sk_test_51HwRfSJbvy9FUePC4IlQ0NJcBp0sMAhOEtLjRuYHKGEhcO9KqUGa7EJ4HRRGsXz3O9x5aFDe8QdAa6R7Oddr6wFi00Tby8HkR7";
            CustomerRetrieveParams retrieveParams
                    = CustomerRetrieveParams.builder()
                            .addExpand("sources")
                            .build();
            try {
                com.stripe.model.Customer a = com.stripe.model.Customer.retrieve("cus_IXWnZejb8QWL0h", retrieveParams, null);
                Map<String, Object> chargeParameter = new HashMap<String, Object>();

                chargeParameter.put("amount", Math.round(Double.parseDouble(donationAmount.getText())) * 100);
                chargeParameter.put("currency", "usd");
                chargeParameter.put("customer", a.getId());
                Charge.create(chargeParameter);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                System.out.println(gson.toJson(a));
            } catch (StripeException s) {
                System.out.println(s.getMessage());
            }

            send("bookycorp.tn@gmail.com", "booky123456789", "nairimalek10@gmail.com", "AUTOMATIC MESSAGE from booky.tn DO NOT REPLY", "Your donation to "+charity.getTitle() +" has been successfully added, thank you for making a positive impact on the world.");

            thankyouMsg.setVisible(true);

        }
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
