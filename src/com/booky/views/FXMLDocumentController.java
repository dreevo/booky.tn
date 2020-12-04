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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Cool IT Help
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TilePane tilePane;

    @FXML
    private AnchorPane myAnchor;
    ObservableList<Book> bookList = FXCollections.observableArrayList();

    int count = 0;

    private int nRows = 3;  //no of row for tile pane
    private int nCols = 3;  // no of column for tile pane

    private static final double ELEMENT_SIZE = 200;
    private static final double GAP = ELEMENT_SIZE / 20;
    private HashMap<Integer, Integer> bookQuantities = new HashMap<>();

    File filesJpg[]; // file array to store read images info
    @FXML
    private Label cartItems;
    private int totalQuantities = 0;
    private double cartTotal = 0;
    @FXML
    private ImageView cartBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tilePane.setPrefColumns(nCols);
        tilePane.setPrefRows(nRows);
        //myAnchor.setStyle("-fx-background-image: Backgound.png;"); //-fx-background-color: rgba(0, 0, 0);
        //tilePane.setStyle("-fx-background-color: rgba(0, 0, 0);");
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        BookService bs = new BookService();
        bs.readBooks(bookList);
        nRows = bookList.size() / 3;
        createElements(bookList);
        cartBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/CartDetails.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    CartDetailsController controller = fxmlLoader.<CartDetailsController>getController();
//                    controller.setBookList(bookList);
//                    controller.setBookQuantities(cartTotal);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("Cart Details");
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
            for (int j = 0; j < 7; j++) {
                tilePane.getChildren().add(createPage(count, bookList.get(j).getImageUrl(), bookList.get(j).getLabel(), bookList.get(j).getId(), bookList.size(), bookList.get(j).getPrice()));
                count++;
            }
    }

    public VBox createPage(int index, String imageUrl, String bookLabel, int bookId, int size, double price) {
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
        Label bookPrice = new Label();
        bookPrice.setText(price+" DT");
        bookPrice.setStyle("-fx-alignment:center;");
        Button b1 = new Button();
        Button b2 = new Button();
        b1.setText("Details");
        b2.setText("Add to Cart");
        // GET THE QUANTITY OF EACH BOOK FROM DATABASE 
        CartItemService cis = new CartItemService();
        int bookQuantity = cis.readCartItem(bookId);
        // TOTAL QUANTITIES IS THE TOTAL ITEMS OF CART 
        totalQuantities += bookQuantity;
        cartTotal += bookQuantity * price;
        bookQuantities.put(bookId, bookQuantity);
        cartItems.setText(totalQuantities + "");
        //bookQuantities.put(bookId, 0);
        //System.out.println(cartTotal);
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                bookQuantities.put(bookId, bookQuantities.get(bookId) + 1);
                totalQuantities+=1;
                cartItems.setText(totalQuantities + "");
                if (bookQuantities.get(bookId) == 1) {
                    CartItem ci = new CartItem(new Book(bookId), 1, new Cart(1));
                    cis.createCartItem(ci);
                } else {
                    CartItem ci = new CartItem(new Book(bookId), bookQuantities.get(bookId), new Cart(1));
                    cis.updateCartItem(ci);
                }
                //incrementTotalQuantity()
                //System.out.println(("Book "+bookId+"has "+bookQuantities.get(bookId)+ " items."));
            }
        });
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(b1);
        buttonBox.getChildren().add(b2);
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
    
    @FXML
    private void incrementTotalQuantity(ActionEvent event) {
        

    }
}
