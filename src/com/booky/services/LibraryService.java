/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Library;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gharbimedaziz
 */
public class LibraryService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createLibrary(Library l) {
        try {
            String req = "INSERT INTO library (name,address,telephone,email) VALUES(?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, l.getName());
            st.setString(2, l.getAddress());
            st.setString(3, l.getTelephone());
            st.setString(4, l.getEmail());
            st.executeUpdate();
            System.out.println("+ LIBRARY ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteLibrary(Library l) {
        try {
            String req = "delete from library where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, l.getId());
            st.executeUpdate();
            System.out.println("+ LIBRARY DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateLibrary(Library l) {
        try {
            String req = "update library set name=?,address=?,telephone=?,email=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, l.getName());
            st.setString(2, l.getAddress());
            st.setString(3, l.getTelephone());
            st.setString(4, l.getEmail());
            st.setInt(5, l.getId());
            st.executeUpdate();
            System.out.println("+ LIBRARY UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void readLibrary(int libraryId) {
        try {
            String req = "select * from library where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, libraryId);
            ResultSet res = st.executeQuery();
            while(res.next()){
                String name = res.getString(2);
                System.out.println(name);
                String address = res.getString(3);
                System.out.println(address);
                String telephone = res.getString(4);
                System.out.println(telephone);
                String email = res.getString(5);
                System.out.println(email);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
