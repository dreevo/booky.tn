/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.CartItem;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            String req = "delete from cartitem where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, cartItemId);
            st.executeUpdate();
            System.out.println("+ CART ITEM DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCartItem(CartItem c) {
        try {
            String req = "update cartitem set quantity=?,bookid=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setInt(1, c.getQuantity());
            st.setInt(2, c.getBook().getId());
            st.setInt(3, c.getId());
            st.executeUpdate();
            System.out.println("+ CART ITEM UPDATED IN DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readCartItem(int cartItemId) {
        try {
            // GETTING THE PACK OBJECT
            String req = "select c.quantity, b.label,b.price,b.description from cartitem c join book b on c.bookid=b.id and c.id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, cartItemId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                int quantity = res.getInt(1);
                System.out.println(quantity);
                String label = res.getString(2);
                System.out.println(label);
                Double price = res.getDouble(3);
                System.out.println(price);
                String description = res.getString(4);
                System.out.println(description);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
