/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Customer;
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
public class CustomerService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void addCustomer(Customer c) {
        try {
            // GETTING THE ROLE ID 
            String query = "SELECT id from role where rolename='"+c.getRole().getRoleName()+"'";
            Statement st = cnx.createStatement();
            ResultSet res =  st.executeQuery(query);
            int roleid=0;
            while(res.next()){
                roleid = res.getInt(1);
            }
            String req = "INSERT INTO customer (firstname,lastname,age,email,address,telephone,roleid) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, c.getFirstName());
            pst.setString(2, c.getLastName());
            pst.setInt(3, c.getAge());
            pst.setString(4, c.getEmail());
            pst.setString(5, c.getAddress());
            pst.setString(6, c.getTelephone());
            pst.setInt(7, roleid);
            pst.executeUpdate();
            System.out.println("+ CUSTOMER ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
