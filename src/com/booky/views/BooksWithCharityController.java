/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.services.CharityService;
import com.booky.entities.Charity;

import java.util.ArrayList;

import com.booky.entities.Book;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class BooksWithCharityController implements Initializable {

    @FXML
    private TilePane tilePane;

    ObservableList<Book> bookList = FXCollections.observableArrayList();

    int count = 0;
    private int totalQuantities = 0;
    private double cartTotal = 0;

    private int nRows = 3;  //no of row for tile pane
    private int nCols = 3;  // no of column for tile pane

    private static final double ELEMENT_SIZE = 150;
    private static final double GAP = ELEMENT_SIZE / 17;
    @FXML
    private Label indexBtn;
    @FXML
    private Label orderPanelBtn;
    @FXML
    private Label donationsPage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tilePane.setPrefColumns(nCols);
        tilePane.setPrefRows(nRows);
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        // Add to cart FADE IN 
        CharityService cs = new CharityService();
        cs.readBooksCharity(bookList);

        nRows = bookList.size() / 3;
        createElements(bookList);
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
        donationsPage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Stage stage1 = (Stage) donationsPage.getScene().getWindow();
                    stage1.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ReadDonations.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setTitle("Donations List");
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    private void createElements(ObservableList<Book> bookList) {
        tilePane.getChildren().clear();
        for (int j = 0; j < bookList.size(); j++) {
            tilePane.getChildren().add(createPage(bookList.get(j).getLabel(), bookList.get(j).getImageUrl()));
            System.out.println(bookList.get(j));
        }

    }

    public VBox createPage(String label, String imageUrl) {
        ImageView imageView = new ImageView();
        try {
            InputStream stream = new FileInputStream(imageUrl);
            Image image = new Image(stream);
            imageView.setImage(image);
            imageView.setFitWidth(ELEMENT_SIZE);
            imageView.setFitHeight(ELEMENT_SIZE);
            imageView.setClip(null);
            imageView.setTranslateY(10);
            imageView.setEffect(new DropShadow(20, Color.BLACK));
            imageView.setSmooth(true);
            imageView.setCache(true);
        } catch (IOException ex) {
        }
        Label booklabel = new Label();
        booklabel.setText(label);
        booklabel.setStyle("-fx-text-fill:white;");

        HBox buttonBox = new HBox();
        Button donate = new Button();
        donate.setText("fund raising");
        donate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Stage stage1 = (Stage) donate.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/CreateDonation.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    //stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setTitle("Donation");
                    stage.setScene(new Scene(root1));
                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.show();
                    stage1.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        buttonBox.getChildren().add(donate);
        buttonBox.setAlignment(Pos.CENTER);
        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);

        pageBox.getChildren().add(booklabel);

        pageBox.getChildren().add(buttonBox);

        pageBox.setPrefWidth(ELEMENT_SIZE + 30);
        pageBox.getStyleClass().add("book-container");
        return pageBox;

    }
}
