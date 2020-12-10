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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author J.Maroua
 */
public class CustomerService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void addCustomer(Customer c) {
        try {
            String req = "INSERT INTO customer (firstname,lastname,age,email,password,address,telephone,roleid) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, c.getFirstName());
            pst.setString(2, c.getLastName());
            pst.setInt(3, c.getAge());
            pst.setString(4, c.getEmail());
            pst.setString(5, BCrypt.hashpw(c.getPassword(), BCrypt.gensalt()));
            pst.setString(6, c.getAddress());
            pst.setString(7, c.getTelephone());
            pst.setInt(8, c.getRole().getId());
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

    public void updateCustomer(Customer c, int id) {
        try {
            String req = "update customer set firstname=?, lastname=?, age=?, email=?, address=?, telephone=?, imageurl=? where id=" + id;
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, c.getFirstName());
            st.setString(2, c.getLastName());
            st.setInt(3, c.getAge());
            st.setString(4, c.getEmail());
            st.setString(5, c.getAddress());
            st.setString(6, c.getTelephone());
            st.setString(7, c.getImageUrl());
            System.out.println(c);
            System.out.println(req);
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

    public boolean validateLogin(String email, String password) {
        boolean validate = false;
        try {
            String req = "select count(1) from customer where email=? and password=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                if (res.getInt(1) == 1) {
                    validate = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return validate;
    }

    public int idlogin(String email, String pass) {
        int a = 0;
        String password = "";
        try {
            String req = "select id,password from Customer " + "where email ='" + email + "'";

            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                password = res.getString("password");
                if (BCrypt.checkpw(pass, password)) {
                    return a = res.getInt("id");
                } else {
                    return 0;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public Customer showcustomer(int id) {
        Customer c = null;
        try {
            String req = "select * from Customer where id=" + id;
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                c = new Customer(res.getString("firstname"), res.getString("lastname"), res.getInt("age"), res.getString("email"), res.getString("password"), res.getString("address"), res.getString("telephone"), res.getString("imageUrl"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
//          public int validateLogin(String email, String password) {
//        int customerId = 0;
//        try {
//            String req = "select id from customer where email=? and password=?";
//            PreparedStatement st = cnx.prepareStatement(req);
//            st.setString(1, email);
//            st.setString(2, password);
//            ResultSet res = st.executeQuery();
//            while (res.next()) {
//                customerId=res.getInt(1);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return customerId;
//    }

    public boolean authentification(String email, String pass) {
        String password = "";
        try {
            String req = "select password from Customer " + "where email ='" + email + "'";
            PreparedStatement st = cnx.prepareStatement(req);
//                      st.setString(1, username);
//			st.setString(2, pass);
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                password = res.getString("password");
            }
            if (!(pass.length() == 0) && !(password.length() == 0)) {
                if (BCrypt.checkpw(pass, password)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public int validateEmail(String email) {
        int count = 0;
        try{
            String req = "select count(1) from customer where email=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, email);
            ResultSet res = st.executeQuery();
            while(res.next()){
                if(res.getInt(1) == 1)
                    count+=1;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return count;
    }

}
