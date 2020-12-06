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
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private int totalQuantities = 0;
    private double cartTotal = 0;

    private int nRows = 3;  //no of row for tile pane
    private int nCols = 1;  // no of column for tile pane

    private static final double ELEMENT_SIZE = 50;
    private static final double GAP = ELEMENT_SIZE / 10;
    private HashMap<Integer, Integer> bookQuantities = new HashMap<>();

    File filesJpg[]; // file array to store read images info

    @FXML
    private Label cartToal;
    @FXML
    private ImageView backBtn;
    @FXML
    private Button checkoutBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tilePane.setPrefColumns(1);
        tilePane.setPrefRows(3);
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        CartItemService cis = new CartItemService();
        cis.readCartItems(bookQuantities, bookList);
        nRows = bookList.size() / 3;
        System.out.println(bookList);
        System.out.println(bookQuantities);
        createElements(bookList);
        backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Stage stage1 = (Stage) backBtn.getScene().getWindow();
                    stage1.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Index.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setTitle("booky.tn");
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
            imageView.setTranslateY(10);
            imageView.setTranslateX(10);
            imageView.setClip(null);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } catch (IOException ex) {
        }
        Label label = new Label();
        label.setText(bookLabel);
        label.setTranslateX(10);
        label.getStyleClass().add("bookLabel");
        Label quantity = new Label();
        Label bookPrice = new Label();
        bookPrice.setText(price + " DT");
        bookPrice.setTranslateX(10);
        bookPrice.getStyleClass().add("bookPrice");
        quantity.setText(bookQuantities.get(bookId) + "");
        quantity.getStyleClass().add("bookLabel");
        DecimalFormat df = new DecimalFormat("#.##");
        cartTotal += bookQuantities.get(bookId) * price;
        cartTotal = Double.valueOf(df.format(cartTotal));
        cartToal.setText(cartTotal + "");
        ImageView deleteItem = new ImageView();
        ImageView reduceQuantity = new ImageView();
        ImageView incrementQuantity = new ImageView();
        try {
            Image addToCartBtn = new Image(new FileInputStream("F:\\21655\\Documents\\NetBeansProjects\\booky\\src\\com\\booky\\views\\images\\deleteitem.png"));
            deleteItem.setImage(addToCartBtn);
            deleteItem.setFitWidth(35);
            deleteItem.setFitHeight(35);
            deleteItem.getStyleClass().add("add-to-cart");
            Image addToCartBtn2 = new Image(new FileInputStream("F:\\21655\\Documents\\NetBeansProjects\\booky\\src\\com\\booky\\views\\images\\minus.png"));
            reduceQuantity.setImage(addToCartBtn2);
            reduceQuantity.setFitWidth(35);
            reduceQuantity.setFitHeight(35);
            reduceQuantity.getStyleClass().add("add-to-cart");
            Image addToCartBtn3 = new Image(new FileInputStream("F:\\21655\\Documents\\NetBeansProjects\\booky\\src\\com\\booky\\views\\images\\plus.png"));
            incrementQuantity.setImage(addToCartBtn3);
            incrementQuantity.setFitWidth(35);
            incrementQuantity.setFitHeight(35);
            incrementQuantity.getStyleClass().add("add-to-cart");
        } catch (IOException ex) {
        }
        deleteItem.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                CartItemService cis = new CartItemService();
                cis.deleteCartItem(bookId);
                Stage stage = (Stage) deleteItem.getScene().getWindow();
                stage.close();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CartDetails.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage registerStage = new Stage();
                    registerStage.initModality(Modality.APPLICATION_MODAL);
                    registerStage.initStyle(StageStyle.TRANSPARENT);
                    registerStage.setTitle("Cart Details");
                    registerStage.setScene(scene);
                    registerStage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                cartTotal -= bookQuantities.get(bookId) * price;
                Cart c = new Cart(1, new Customer(1), null, cartTotal);
                CartService cs = new CartService();
                cs.updateCart(c);
                cartToal.setText(cartTotal + "");
            }
        });
        incrementQuantity.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
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
                cartTotal += price;
                Cart c = new Cart(1, new Customer(1), null, cartTotal);
                CartService cs = new CartService();
                cs.updateCart(c);
                cartToal.setText(cartTotal + "");
            }
        });
        reduceQuantity.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                CartItemService cis = new CartItemService();
                bookQuantities.put(bookId, bookQuantities.get(bookId) - 1);
                totalQuantities -= 1;
                quantity.setText(bookQuantities.get(bookId) + "");
                if (bookQuantities.get(bookId) == 0) {
                    cis.deleteCartItem(bookId);
                    Stage stage = (Stage) deleteItem.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartDetails.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage registerStage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.TRANSPARENT);
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
                cartTotal -= price;
                Cart c = new Cart(1, new Customer(1), null, cartTotal);
                CartService cs = new CartService();
                cs.updateCart(c);
                cartToal.setText(cartTotal + "");
            }
        });
        HBox itemLine = new HBox();
        itemLine.getChildren().add(imageView);
        itemLine.getChildren().add(label);
        itemLine.getChildren().add(bookPrice);
        itemLine.setAlignment(Pos.CENTER_LEFT);
        HBox buttonsSection = new HBox();
        buttonsSection.getChildren().add(reduceQuantity);
        buttonsSection.getChildren().add(quantity);
        buttonsSection.getChildren().add(incrementQuantity);
        buttonsSection.getChildren().add(deleteItem);
        buttonsSection.setAlignment(Pos.TOP_RIGHT);
        buttonsSection.setTranslateX(-20);
        VBox pageBox = new VBox();
        pageBox.getChildren().add(itemLine);
        pageBox.getChildren().add(buttonsSection);
        Line line1 = new Line(800, 0, 0, 0);
        line1.setStroke(Color.LIGHTGRAY);
        pageBox.getChildren().add(line1);
        pageBox.getStyleClass().add("cart-item-container");
        pageBox.setPrefWidth(700);
        pageBox.setSpacing(GAP);
        imageView = null;
        return pageBox;
    }

    @FXML
    private void proceedToCheckout(ActionEvent event) {
        try {
            Stage stage1 = (Stage) backBtn.getScene().getWindow();
            stage1.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Checkout.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("booky.tn");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
