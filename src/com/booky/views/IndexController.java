/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.views;

import com.booky.entities.Book;
import com.booky.entities.Cart;
import com.booky.entities.CartItem;
import com.booky.entities.Customer;
import com.booky.services.BookService;
import com.booky.services.CartItemService;
import com.booky.services.CartService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
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
import javafx.util.Duration;

/**
 *
 * @author gharbimedaziz
 */
public class IndexController implements Initializable {

    @FXML
    private TilePane tilePane;

    @FXML
    private AnchorPane myAnchor;

    ObservableList<Book> bookList = FXCollections.observableArrayList();

    int count = 0;
    private int totalQuantities = 0;
    private double cartTotal = 0;

    private int nRows = 3;  //no of row for tile pane
    private int nCols = 3;  // no of column for tile pane

    private static final double ELEMENT_SIZE = 150;
    private static final double GAP = ELEMENT_SIZE / 17;
    private HashMap<Integer, Integer> bookQuantities = new HashMap<>();

    File filesJpg[]; // file array to store read images info
    @FXML
    private Label cartItems;

    @FXML
    private ImageView cartBtn;
    @FXML
    private Label addedMessageLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tilePane.setPrefColumns(nCols);
        tilePane.setPrefRows(nRows);
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        // Add to cart FADE IN 
        addedMessageLabel.setVisible(false);
        addedMessageLabel.setText("+ Added to cart");
        addedMessageLabel.setStyle("-fx-text-fill:#92fd9b");
        BookService bs = new BookService();
        bs.readBooks(bookList);
        System.out.println(bookList);
        nRows = bookList.size() / 3;
        createElements(bookList);
        cartBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (cartItems.getText().equals("0")) {
                    addedMessageLabel.setText("Your cart is empty !");
                    addedMessageLabel.setStyle("-fx-text-fill:#ff6b03");
                    addedMessageLabel.setVisible(true);
                    fadeIn.setNode(addedMessageLabel);
                    fadeIn.setAutoReverse(false);
                    fadeIn.setToValue(0);
                    fadeIn.setFromValue(1);
                    fadeIn.play();
                } else {
                    try {
                        Stage stage1 = (Stage) cartBtn.getScene().getWindow();
                        stage1.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/CartDetails.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setTitle("Cart Details");
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
    }

    private void createElements(ObservableList<Book> bookList) {
        tilePane.getChildren().clear();
        for (int j = 0; j < bookList.size(); j++) {
            tilePane.getChildren().add(createPage(count, bookList.get(j).getImageUrl(), bookList.get(j).getLabel(), bookList.get(j).getId(), bookList.size(), bookList.get(j).getPrice(), bookList.get(j).getRating()));
            count++;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        cartTotal = Double.valueOf(df.format(cartTotal));
        Cart c = new Cart(1, new Customer(1), null, cartTotal);
        CartService cs = new CartService();
        cs.updateCart(c);
    }

    public VBox createPage(int index, String imageUrl, String bookLabel, int bookId, int size, double price, int rating) {
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
        Label label = new Label();
        Label detailsLabel = new Label();
        label.setText(bookLabel);
        detailsLabel.setText("Details");
        detailsLabel.getStyleClass().add("detailsLabel");
        label.getStyleClass().add("bookLabel");
        Label bookPrice = new Label();
        bookPrice.setText(price + " DT");
        bookPrice.setStyle("-fx-alignment:center;");
        ImageView addTocartBtn = new ImageView();
        ImageView bookDetailsBtn = new ImageView();
        ImageView ratingIcon = new ImageView();
        try {
            Image addToCartBtn = new Image(new FileInputStream("F:\\21655\\Documents\\NetBeansProjects\\booky\\src\\com\\booky\\views\\images\\addcart.png"));
            addTocartBtn.setImage(addToCartBtn);
            addTocartBtn.setFitWidth(35);
            addTocartBtn.setFitHeight(35);
            addTocartBtn.getStyleClass().add("add-to-cart");
            Glow glow = new Glow(0.5);
            addTocartBtn.setEffect(glow);
            Image detailsBtn = new Image(new FileInputStream("F:\\21655\\Documents\\NetBeansProjects\\booky\\src\\com\\booky\\views\\images\\details.png"));
            bookDetailsBtn.setImage(detailsBtn);
            bookDetailsBtn.setFitWidth(30);
            bookDetailsBtn.setFitHeight(30);
            bookDetailsBtn.getStyleClass().add("add-to-cart");
            bookDetailsBtn.setEffect(glow);
            Image ratingImage = new Image(new FileInputStream("F:\\21655\\Documents\\NetBeansProjects\\booky\\src\\com\\booky\\views\\images\\star.png"));
            ratingIcon.setImage(ratingImage);
            ratingIcon.setFitWidth(20);
            ratingIcon.setFitHeight(20);
            ratingIcon.setEffect(glow);
        } catch (IOException ex) {
        }
        // GET THE QUANTITY OF EACH BOOK FROM DATABASE 
        CartItemService cis = new CartItemService();
        int bookQuantity = cis.readCartItem(bookId);
        // TOTAL QUANTITIES IS THE TOTAL ITEMS OF CART 
        totalQuantities += bookQuantity;
        cartTotal += bookQuantity * price;
        bookQuantities.put(bookId, bookQuantity);
        cartItems.setText(totalQuantities + "");
        addTocartBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bookQuantities.put(bookId, bookQuantities.get(bookId) + 1);
                totalQuantities += 1;
                cartItems.setText(totalQuantities + "");
                if (bookQuantities.get(bookId) == 1) {
                    CartItem ci = new CartItem(new Book(bookId), 1, new Cart(1));
                    cis.createCartItem(ci);

                } else {
                    CartItem ci = new CartItem(new Book(bookId), bookQuantities.get(bookId), new Cart(1));
                    cis.updateCartItem(ci);
                }
                System.out.println(cartTotal);
                Cart c = new Cart(1, new Customer(1), null, cartTotal);
                CartService cs = new CartService();
                cs.updateCart(c);
                addedMessageLabel.setStyle("-fx-text-fill:#92fd9b");
                addedMessageLabel.setText("+ Added to cart");
                addedMessageLabel.setVisible(true);
                fadeIn.setNode(addedMessageLabel);
                fadeIn.setAutoReverse(false);
                fadeIn.setToValue(0);
                fadeIn.setFromValue(1);
                fadeIn.play();
            }
        });
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(bookDetailsBtn);
        buttonBox.getChildren().add(detailsLabel);
        buttonBox.getChildren().add(addTocartBtn);
        buttonBox.setAlignment(Pos.CENTER);
        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);
        pageBox.getChildren().add(label);
        pageBox.getChildren().add(buttonBox);
        HBox priceAndRating = new HBox();
        bookPrice.getStyleClass().add("priceLabel");
        priceAndRating.getChildren().add(bookPrice);
        Label ratingLabel = new Label();
        ratingLabel.setText(rating + "");
        ratingLabel.getStyleClass().add("ratingLabel");
        priceAndRating.getChildren().add(ratingLabel);
        priceAndRating.getChildren().add(ratingIcon);
        priceAndRating.setAlignment(Pos.CENTER);
        ratingLabel.getStyleClass().add("price-rating-container");
        pageBox.getChildren().add(priceAndRating);
        pageBox.getStyleClass().add("card");
        pageBox.setPrefWidth(ELEMENT_SIZE + 30);
        pageBox.getStyleClass().add("book-container");
        imageView = null;
        return pageBox;
    }

    private FadeTransition fadeIn = new FadeTransition(
            Duration.millis(1000), addedMessageLabel
    );

}
