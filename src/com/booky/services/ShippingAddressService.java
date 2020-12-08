/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.ShippingAddress;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gharbimedaziz
 */
public class ShippingAddressService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createShippingAddress(ShippingAddress sh) {
        try {
            String req = "INSERT INTO shippingAddress (address,telephone,city,zipcode,orderid) VALUES(?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, sh.getAddress());
            st.setString(3, sh.getCity());
            st.setInt(4, sh.getZipcode());
            st.setInt(5, sh.getOrder().getId());
            st.setString(2, sh.getTelephone());
            st.executeUpdate();
            System.out.println("+ SHIPPINGADDRESS ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteShippingAddress(int id) {
        try {
            String req = "delete from shippingaddress where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("+ SHIPPINGADDRESS DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}
