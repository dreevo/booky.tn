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

/**
 *
 * @author gharbimedaziz
 */
public class CustomerService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void addCustomer(Customer c) {
        try {
            String req = "INSERT INTO customer (firstname,lastname,age,email,address,telephone,roleid,password) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, c.getFirstName());
            pst.setString(2, c.getLastName());
            pst.setInt(3, c.getAge());
            pst.setString(4, c.getEmail());
            pst.setString(5, c.getAddress());
            pst.setString(6, c.getTelephone());
            pst.setString(8, c.getPassword());
            pst.setInt(7, 2);
            pst.executeUpdate();
            System.out.println("+ CUSTOMER ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomer(int customerId) {
        try {
            String req = "delete from customer where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, customerId);
            st.executeUpdate();
            System.out.println("+ CUSTOMER DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCustomer(Customer c) {
        try {
            String req = "update customer set firstname=?, lastname=?, age=?, email=?, address=?, telephone=?, imageurl=?, roleid=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, c.getFirstName());
            st.setString(2, c.getLastName());
            st.setInt(3, c.getAge());
            st.setString(4, c.getEmail());
            st.setString(5, c.getAddress());
            st.setString(6, c.getTelephone());
            st.setString(7, c.getImageUrl());
            st.setInt(8, c.getRole().getId());
            st.setInt(9, c.getId());
            st.executeUpdate();
            System.out.println("+ CUSTOMER UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readCustomer(int customerId) {
        try {
            // GETTING THE CUSTOMER OBJECT
            String req = "select * from customer where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, customerId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String firstName = res.getString(2);
                System.out.println(firstName);
                String lastName = res.getString(3);
                System.out.println(lastName);
                int age = res.getInt(4);
                System.out.println(age);
                String address = res.getString(5);
                System.out.println(address);
                String telephone = res.getString(6);
                System.out.println(telephone);
                String imageurl = res.getString(7);
                System.out.println(imageurl);
                int roleId = res.getInt(8);
                System.out.println(roleId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean validateLogin(String email, String password){
        boolean validate=false;
        try{
            String req = "select count(1) from customer where email=? and password=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet res = st.executeQuery();
            while(res.next()){
                if(res.getInt(1) == 1)
                    validate=true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return validate;
    }

}
