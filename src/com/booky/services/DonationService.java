/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Customer;
import com.booky.entities.Donation;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gharbimedaziz
 */
public class DonationService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createDonation(Donation d) {
        //Adding a donation in the donation table
        try {
            String req = "INSERT INTO donation (message,amount,customerid,charityid) VALUES(?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(3, d.getCustomer().getId());
            st.setString(1, d.getMessage());
            st.setDouble(2, d.getAmount());
            st.setInt(4, d.getCharity().getId());
            st.executeUpdate();
            System.out.println("+ DONATION ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDonation(int donationId) {
        try {
            String req = "delete from donation where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, donationId);
            st.executeUpdate();
            System.out.println("+ DONATION DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateDonation(Donation d) {
        try {
            String req = "update donation set message=?,amount=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, d.getMessage());
            st.setDouble(2, d.getAmount());
            st.setInt(3, d.getId());
            st.executeUpdate();
            System.out.println("+ DONATION UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Donation readDonation(int donationId) {
        Donation don = null;
        try {
            String req = "SELECT * from donation where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, donationId);
            ResultSet res = st.executeQuery();
            String message;
            Double amount;
            int customerId=0;
            int charityId=0;
            while (res.next()) {
                message = res.getString(2);
                System.out.println(message);
                amount = res.getDouble(3);
                System.out.println(amount);
                customerId = res.getInt(4);
                System.out.println(customerId);
                charityId = res.getInt(5);
                System.out.println(charityId);
            }
            // GETTING CUSTOMER OF THE DONATION
            String req1 = "select * from customer where id=?";
            PreparedStatement st1 = cnx.prepareStatement(req1);
            st1.setInt(1, customerId);
            ResultSet res1 = st1.executeQuery();
            Customer customer=null;
            while (res1.next()) {
                customer = new Customer(customerId,res1.getString(2),res1.getString(3),res1.getInt(4),res1.getString(5),res1.getString(6),res1.getString(7),res1.getString(8),null);
            }
            System.out.println(customer);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return don;
    }
}
