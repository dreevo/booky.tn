/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Pack;
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
public class PackService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createPack(Pack p) {
        try {
            // ADDING THE PACK IN PACK TABLE
            String req = "INSERT INTO pack (label,price,description,imageurl) VALUES(?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, p.getLabel());
            st.setDouble(2, p.getPrice());
            st.setString(3, p.getDescription());
            st.setString(4, p.getImageUrl());
            st.executeUpdate();
            System.out.println("+ PACK ADDED TO DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deletePack(int packId) {
        try {
            String req = "delete from pack where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, packId);
            st.executeUpdate();
            System.out.println("+ PACK DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePack(Pack p) {
        try {
            String req = "update pack set label=?,price=?,description=?,imageurl=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, p.getLabel());
            st.setDouble(2, p.getPrice());
            st.setString(3, p.getDescription());
            st.setString(4, p.getImageUrl());
            st.setInt(5, p.getId());
            st.executeUpdate();
            System.out.println("+ Pack UPDATED IN DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readPack(int packId) {
        try {
            // GETTING THE PACK OBJECT
            String req = "select * from pack where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, packId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String label = res.getString(2);
                System.out.println(label);
                Double price = res.getDouble(3);
                System.out.println(price);
                String description = res.getString(4);
                System.out.println(description);
                String imageurl = res.getString(5);
                System.out.println(imageurl);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
