/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Cart;
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
public class CartService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createCart(Cart c) {
        try {
            // ADDING THE CART IN CART TABLE
            String req = "INSERT INTO cart (customerid,totalprice) VALUES(?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, c.getCustomer().getId());
            st.setDouble(2, c.getTotalPrice());
            st.executeUpdate();
            System.out.println("+ CART ADDED TO DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteCart(int cartId) {
        try {
            String req = "delete from cart where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, cartId);
            st.executeUpdate();
            System.out.println("+ CART DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updatePack(Cart c) {
        try {
            String req = "update cart set totalprice=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, c.getId());
            st.executeUpdate();
            System.out.println("+ Pack UPDATED IN DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void readCart(int packId) {
        try {
            // GETTING THE PACK OBJECT
            String req = "select cu.firstname,cu.lastname,c.totalprice from cart c join customer cu on c.customerid=cu.id where c.id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, packId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String firstName = res.getString(1);
                System.out.println(firstName);
                String lastName = res.getString(2);
                System.out.println(lastName);
                Double totalPrice = res.getDouble(3);
                System.out.println(totalPrice);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
