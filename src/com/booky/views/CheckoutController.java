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
import com.booky.entities.Order;
import com.booky.entities.ShippingAddress;
import com.booky.services.CartItemService;
import com.booky.services.CartService;
import com.booky.services.OrderService;
import com.booky.services.ShippingAddressService;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import javafx.util.Duration;
import com.itextpdf.text.Document;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * FXML Controller class
 *
 * @author gharbimedaziz
 */
public class CheckoutController implements Initializable {

    ObservableList<Book> bookList = FXCollections.observableArrayList();

    int count = 0;
    private int totalQuantities = 0;
    private double cartTotal = 0;
    private boolean discount = false;

    private int nRows = 3;  //no of row for tile pane
    private int nCols = 1;  // no of column for tile pane

    private static final double ELEMENT_SIZE = 50;
    private static final double GAP = ELEMENT_SIZE / 10;
    private HashMap<Integer, Integer> bookQuantities = new HashMap<>();

    @FXML
    private Label cartToal;
    @FXML
    private TilePane tilePane;
    @FXML
    private ImageView backBtn;
    @FXML
    private TextField addressField;
    @FXML
    private ComboBox<String> orderType;
    @FXML
    private TextField cityField;
    @FXML
    private TextField zipcodeField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField cardNumberField;
    @FXML
    private Label cardNumberLabel;
    @FXML
    private Button discountBtn;
    @FXML
    private Button proceedBtn;
    @FXML
    private TextField discountField;
    @FXML
    private Label discountLabel;
    @FXML
    private Label discountLabelFail;
    @FXML
    private Label requiredFieldLabel;
    @FXML
    private Label billGeneratedLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tilePane.setPrefColumns(1);
        tilePane.setPrefRows(3);
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        orderType.getItems().add("Bank card");
        orderType.getItems().add("By post");
        orderType.getItems().add("Pay on delivery");
        orderType.getStyleClass().add("combo-box");
        discountLabel.setVisible(false);
        discountLabelFail.setVisible(false);
        billGeneratedLabel.setVisible(false);
        requiredFieldLabel.setVisible(false);
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
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/CartDetails.fxml"));
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
        orderType.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
                        if (newValue.equals("Bank card")) {
                            cardNumberField.setVisible(true);
                            cardNumberLabel.setVisible(true);
                        } else {
                            cardNumberField.setVisible(false);
                            cardNumberLabel.setVisible(false);
                        }
                    }
                });
        zipcodeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    zipcodeField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        telephoneField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    telephoneField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        discountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (newValue.length() > 0) {
                    discountBtn.setDisable(false);
                } else {
                    discountBtn.setDisable(true);
                }

            }
        });
        cardNumberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    cardNumberField.setText(newValue.replaceAll("[^\\d-]", ""));
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
        Label label = new Label();
        label.setText(bookLabel + " * ");
        label.setTranslateX(10);
        label.getStyleClass().add("bookLabel");
        Label quantity = new Label();
        Label bookPrice = new Label();
        double totalItem = bookQuantities.get(bookId) * price;
        DecimalFormat df = new DecimalFormat("#.##");
        totalItem = Double.valueOf(df.format(totalItem));
        bookPrice.setText(totalItem + " DT");
        bookPrice.setTranslateX(10);
        bookPrice.getStyleClass().add("bookPrice");
        quantity.setText(bookQuantities.get(bookId) + "");
        quantity.getStyleClass().add("bookLabel");
        cartTotal = Double.valueOf(df.format(cartTotal));
        cartTotal += bookQuantities.get(bookId) * price;
        cartTotal = Double.valueOf(df.format(cartTotal));
        cartToal.setText(cartTotal + "");
        HBox itemLine = new HBox();
        itemLine.getChildren().add(label);
        itemLine.getChildren().add(quantity);
        itemLine.getChildren().add(bookPrice);
        itemLine.setAlignment(Pos.CENTER_LEFT);
        VBox pageBox = new VBox();
        pageBox.getChildren().add(itemLine);
        Line line1 = new Line(800, 0, 0, 0);
        line1.setStroke(Color.LIGHTGRAY);
        pageBox.getChildren().add(line1);
        pageBox.getStyleClass().add("cart-item-container");
        pageBox.setPrefWidth(700);
        pageBox.setSpacing(GAP);
        return pageBox;
    }
    @FXML
    private void proceedToCheckout(ActionEvent event) {
        if (addressField.getText().isEmpty() || cityField.getText().isEmpty() || orderType.getSelectionModel().isEmpty() || zipcodeField.getText().isEmpty() || telephoneField.getText().isEmpty() || (orderType.getValue().equals("Bank card") && cardNumberField.getText().isEmpty())) {
            requiredFieldLabel.setVisible(true);
            fadeIn.setNode(requiredFieldLabel);
            fadeIn.setAutoReverse(false);
            fadeIn.setToValue(0);
            fadeIn.setFromValue(1);
            fadeIn.play();
        } else {
            if (orderType.getValue().equals("By post")) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                OrderService os = new OrderService();
                Order or = new Order();
                if(discount == true)or.setDiscount(0);
                else or.setDiscount(20);
                or.setOrderType("By post");
                Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
                or.setDate(sqlDate);
                or.setIsDone(0);
                or.setCart(new Cart(1));
                int orderId = os.createOrder(or);
                ShippingAddressService sas = new ShippingAddressService();
                ShippingAddress sa = new ShippingAddress();
                sa.setAddress(addressField.getText());
                sa.setTelephone(telephoneField.getText());
                sa.setCity(cityField.getText());
                sa.setZipcode(Integer.parseInt(zipcodeField.getText()));
                sa.setOrder(new Order(orderId));
                sas.createShippingAddress(sa);
                try {
                    Document doc = new Document();
                    PdfWriter.getInstance(doc, new FileOutputStream("F:/bill.pdf"));
                    doc.open();
                    System.out.println(dtf.format(now));
                    doc.add(new Paragraph("booky.tn Order at : " + now));
                    doc.add(new Paragraph("Type of order : " + "By post."));
                    doc.add(new Paragraph("Order items : "));
                    for (int i = 0; i < bookList.size(); i++) {
                        doc.add(new Paragraph("Item :  " + bookList.get(i).getLabel() + " , Price : " + bookList.get(i).getPrice() + " DT , Quantity : " + bookQuantities.get(bookList.get(i).getId())));
                    }
                    doc.add(new Paragraph("Order Total : " + cartTotal + " DT."));
                    if (discount == true) {
                        doc.add(new Paragraph("Discount : 20%"));
                        doc.add(new Paragraph("Total with discount : " + (cartTotal - cartTotal * 0.2) + " DT."));
                    }
                    doc.add(new Paragraph("Customer's telephone : " + telephoneField.getText()));
                    doc.add(new Paragraph("Customer's address : " + addressField.getText()));
                    doc.add(new Paragraph("Customer's city : " + cityField.getText()));
                    doc.add(new Paragraph("Please make sure to bring this bill to the post to be able to retrieve your items !"));
                    doc.add(new Paragraph("booky.tn Admin."));
                    doc.close();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                billGeneratedLabel.setVisible(true);
                fadeIn.setNode(billGeneratedLabel);
                fadeIn.setAutoReverse(false);
                fadeIn.setToValue(0);
                fadeIn.setFromValue(1);
                fadeIn.play();
            }
        }
    }

    @FXML
    private void verifyDiscount(ActionEvent event) {
        if (discountField.getText().equals("discountcode")) {
            discount = true;
            discountBtn.setDisable(true);
            discountField.setText("");
            discountLabel.setVisible(true);
            fadeIn.setNode(discountLabel);
            fadeIn.setAutoReverse(false);
            fadeIn.setToValue(0);
            fadeIn.setFromValue(1);
            fadeIn.play();
        } else {
            discountLabelFail.setVisible(true);
            fadeIn.setNode(discountLabelFail);
            fadeIn.setAutoReverse(false);
            fadeIn.setToValue(0);
            fadeIn.setFromValue(1);
            fadeIn.play();
        }

    }

    private FadeTransition fadeIn = new FadeTransition(
            Duration.millis(1000), discountLabel
    );

}
