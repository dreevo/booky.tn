/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Charity;
import com.booky.entities.Donation;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author J.Maroua
 */
public class CharityService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createCharity(Charity c) {
        try {
            String req = "INSERT INTO charity (title,description,imageUrl) VALUES(?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, c.getTitle());
            st.setString(2, c.getDescription());
            st.setString(3, c.getImageUrl());
            st.executeUpdate();
            System.out.println("+ Charity ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
     public void deleteCharity(int charityId) {
        try {
            String req = "delete from charity where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, charityId);
            st.executeUpdate();
            System.out.println("+ CHARITY DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     
      public void updateCharity(Charity c) {
        try {
            String req = "update charity set title=?,description=?,imageUrl=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, c.getTitle());
            st.setString(2, c.getDescription());
            st.setString(3,c.getImageUrl());
            st.setInt(4,c.getId());
            st.executeUpdate();
            System.out.println("+ CHARITY UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
      
      public void readCharity(int charityId) {
        Charity charity = null;
        try {
            // READING ALL THE CHARITY TABLE DATA
            String req = "select * from charity where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, charityId);
            ResultSet res = st.executeQuery();
            while(res.next()){
                String title = res.getString(2);
                System.out.println(title);
                String description = res.getString(3);
                System.out.println(description);
                String imageUrl = res.getString(4);
                System.out.println(imageUrl);
            }
            // GETTING DONATIONS IDS OF THE CHARITY
            String req1 = "select * from donation where charityid=?";
            PreparedStatement st1 = cnx.prepareStatement(req1);
            st1.setInt(1, charityId);
            ResultSet res1 = st1.executeQuery();
            ArrayList<Integer> donationsIds = new ArrayList<>();
            while (res1.next()) {
                donationsIds.add(res1.getInt(1));
            }
            System.out.println(donationsIds);
            // GETTING THE LIST OF DONATIONS
            ArrayList<Donation> donations = new ArrayList<>();
            for (int i = 0; i < donationsIds.size(); i++) {
                String req2 = "select * from donation where id=?";
                PreparedStatement st2 = cnx.prepareStatement(req2);
                st2.setInt(1, donationsIds.get(i));
                ResultSet res2 = st2.executeQuery();
                while (res2.next()) {
                    donations.add(new Donation(donationsIds.get(i),res2.getString(2), res2.getDouble(3), null));
                }
            }
            System.out.println(donations);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
