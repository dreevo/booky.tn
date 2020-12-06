/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Order;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gharbimedaziz
 */
public class OrderService {

    Connection cnx = DataSource.getInstance().getCnx();

    public int createOrder(Order or) {
        int orderId = 0;
        try {
            String req = "INSERT INTO orders (discount,ordertype,date,isdone,cartid) VALUES(?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, or.getDiscount());
            st.setString(2, or.getOrderType());
            st.setDate(3, or.getDate());
            st.setInt(4, or.getIsDone());
            st.setInt(5, or.getCart().getId());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
            System.out.println("+ ORDER ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderId;
    }

    public void deleteOrder(int orderId) {
        try {
            String req = "delete from order where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, orderId);
            st.executeUpdate();
            System.out.println("+ ORDER DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateOrder(Order o) {
        try {
            String req = "update orders set discount=?,orderType=?,isDone =? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, o.getDiscount());
            st.setString(2, o.getOrderType());
            st.setInt(3, o.getIsDone());
            st.setInt(4, o.getId());
            st.executeUpdate();
            System.out.println("+ ORDER UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readOrder(int orderId) {
        try {
            // GETTING THE ORDER OBJECT
            String req = "select o.discount,o.ordertype,o.date,o.isdone,c.totalprice from orders o join cart c on c.id=o.cartid and o.id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, orderId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String discount = res.getString(1);
                System.out.println(discount);
                String orderType = res.getString(2);
                System.out.println(orderType);
                Date date = res.getDate(3);
                System.out.println(date);
                int isDone = res.getInt(4);
                System.out.println(isDone);
                double totalPrice = res.getDouble(5);
                System.out.println(totalPrice);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
