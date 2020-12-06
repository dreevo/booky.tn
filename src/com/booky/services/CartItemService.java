/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Book;
import com.booky.entities.CartItem;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javafx.collections.ObservableList;

/**
 *
 * @author gharbimedaziz
 */
public class CartItemService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createCartItem(CartItem c) {
        try {
            // ADDING THE CART ITEM IN CARTITEM TABLE
            String req = "INSERT INTO cartitem (quantity,bookid,cartid) VALUES(?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, c.getQuantity());
            st.setInt(2, c.getBook().getId());
            st.setInt(3, c.getCart().getId());
            st.executeUpdate();
            System.out.println("+ CARTITEM ADDED TO DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteCartItem(int cartItemId) {
        try {
            String req = "delete from cartitem where bookid=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, cartItemId);
            st.executeUpdate();
            System.out.println("+ CART ITEM DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteCartItemsAfterOrder(int cartId){
        try {
            String req = "delete from cartitem where cartid=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, cartId);
            st.executeUpdate();
            System.out.println("+ CART ITEM DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCartItem(CartItem c) {
        try {
            String req = "update cartitem set quantity=? where bookid=?";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setInt(1, c.getQuantity());
            st.setInt(2, c.getBook().getId());
            st.executeUpdate();
            System.out.println("+ CART ITEM UPDATED IN DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int readCartItem(int bookId) {
        int quantity=0;
        try {
            // GETTING THE PACK OBJECT
            String req = "select c.quantity from cartitem c where c.bookid=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, bookId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                quantity = res.getInt(1);
                //System.out.println(quantity);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quantity;
    }
    
    public void readCartItems(HashMap<Integer, Integer> bookQuantities, ObservableList<Book> bookList){
        try {
            // GETTING THE PACK OBJECT
            String req = "select c.quantity,b.label,b.price,b.imageUrl,c.bookid from cartitem c join book b on b.id=c.bookid";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Book b = new Book();
                b.setImageUrl(res.getString(4));
                b.setLabel(res.getString(2));
                b.setPrice(res.getDouble(3));
                b.setId(res.getInt(5));
                bookList.add(b);
                bookQuantities.put(res.getInt(5), res.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
