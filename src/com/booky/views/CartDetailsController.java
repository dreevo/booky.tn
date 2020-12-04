/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Book;
import com.booky.entities.Cart;
import com.booky.entities.CartItem;
import com.booky.services.BookService;
import com.booky.services.CartItemService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class CartDetailsController implements Initializable {

    @FXML
    private TilePane tilePane;


    ObservableList<Book> bookList = FXCollections.observableArrayList();

    int count = 0;

    private int nRows = 3;  //no of row for tile pane
    private int nCols = 1;  // no of column for tile pane

    private static final double ELEMENT_SIZE = 100;
    private static final double GAP = ELEMENT_SIZE / 10;
    private HashMap<Integer, Integer> bookQuantities = new HashMap<>();

    File filesJpg[]; // file array to store read images info
    private int totalQuantities = 0;
    private double cartTotal = 0;
    @FXML
    private Label cartToal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tilePane.setPrefColumns(nCols);
        tilePane.setPrefRows(nRows);
        //myAnchor.setStyle("-fx-background-image: Backgound.png;"); //-fx-background-color: rgba(0, 0, 0);
        //tilePane.setStyle("-fx-background-color: rgba(0, 0, 0);");
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        CartItemService cis = new CartItemService();
        cis.readCartItems(bookQuantities, bookList);
        nRows = bookList.size() / 3;
        System.out.println(bookList);
        System.out.println(bookQuantities);
        createElements(bookList);
    }

    private void createElements(ObservableList<Book> bookList) {
        tilePane.getChildren().clear();
        for (int j = 0; j < bookList.size(); j++) {
            tilePane.getChildren().add(createPage(count, bookList.get(j).getImageUrl(), bookList.get(j).getLabel(), bookList.get(j).getId(), bookList.size(), bookList.get(j).getPrice(), bookQuantities));
            count++;
        }
    }

    public VBox createPage(int index, String imageUrl, String bookLabel, int bookId, int size, double price, HashMap<Integer, Integer> bookQuantities) {
        ImageView imageView = new ImageView();
        try {
            InputStream stream = new FileInputStream(imageUrl);
            Image image = new Image(stream);
            imageView.setImage(image);
            imageView.setFitWidth(ELEMENT_SIZE);
            imageView.setFitHeight(ELEMENT_SIZE);
            // imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } catch (IOException ex) {
        }
        Label label = new Label();
        label.setText(bookLabel);
        label.setStyle("-fx-alignment:center;");
        Button b1 = new Button();
        Button b2 = new Button();
        Button b3 = new Button();
        Label quantity = new Label();
        Label bookPrice = new Label();
        bookPrice.setText(price+" DT");
        bookPrice.setStyle("-fx-alignment:center;");
        quantity.setText(bookQuantities.get(bookId) + "");
        cartTotal+=bookQuantities.get(bookId) * price;
        cartToal.setText(cartTotal+"");
        b1.setText("-");
        b2.setText("+");
        b3.setText("Delete");
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CartItemService cis = new CartItemService();
                cis.deleteCartItem(bookId);
                Stage stage = (Stage) b3.getScene().getWindow();
                // do what you have to do
                stage.close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CartDetails.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage registerStage = new Stage();
                    registerStage.setTitle("Cart Details");
                    registerStage.setScene(scene);
                    registerStage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CartItemService cis = new CartItemService();
                bookQuantities.put(bookId, bookQuantities.get(bookId) + 1);
                totalQuantities += 1;
                quantity.setText(bookQuantities.get(bookId) + "");
                if (bookQuantities.get(bookId) == 1) {
                    CartItem ci = new CartItem(new Book(bookId), 1, new Cart(1));
                    cis.createCartItem(ci);
                } else {
                    CartItem ci = new CartItem(new Book(bookId), bookQuantities.get(bookId), new Cart(1));
                    cis.updateCartItem(ci);
                }
            }
        });
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CartItemService cis = new CartItemService();
                bookQuantities.put(bookId, bookQuantities.get(bookId) - 1);
                totalQuantities -= 1;
                quantity.setText(bookQuantities.get(bookId) + "");
                if (bookQuantities.get(bookId) == 0) {
                    cis.deleteCartItem(bookId);
                    Stage stage = (Stage) b3.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartDetails.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage registerStage = new Stage();
                        registerStage.setTitle("Cart Details");
                        registerStage.setScene(scene);
                        registerStage.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    CartItem ci = new CartItem(new Book(bookId), bookQuantities.get(bookId), new Cart(1));
                    cis.updateCartItem(ci);
                }
            }
        });
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(b1);
        buttonBox.getChildren().add(quantity);
        buttonBox.getChildren().add(b2);
        buttonBox.getChildren().add(b3);
        buttonBox.setSpacing(GAP);
        buttonBox.setAlignment(Pos.CENTER);
        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);
        pageBox.getChildren().add(label);
        pageBox.getChildren().add(buttonBox);
        pageBox.getChildren().add(bookPrice);
        pageBox.setStyle("-fx-alignment:center;");
        imageView = null;
        return pageBox;
    }

}
