/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Author;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gharbimedaziz
 */
public class AuthorService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createAuthor(Author a) {
        try {
            String req = "INSERT INTO author (firstName,lastName,age,email,description,imageurl) VALUES(?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, a.getFirstName());
            st.setString(2, a.getLastName());
            st.setInt(3, a.getAge());
            st.setString(4, a.getEmail());
            st.setString(5, a.getDescription());
            st.setString(6, a.getImageUrl());
            st.executeUpdate();
            System.out.println("+ AUTHOR ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAuthor(int AuthorId) {
        try {
            String req = "delete from author where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, AuthorId);
            st.executeUpdate();
            System.out.println("+ AUTHOR DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateAuthor(Author a) {
        try {
            String req = "update author set firstname=?, lastname=?,age=?,email=?, description=?, imageURL=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, a.getFirstName());
            st.setString(2, a.getLastName());
            st.setInt(3, a.getAge());
            st.setString(4, a.getEmail());
            st.setString(5, a.getDescription());
            st.setString(6, a.getImageUrl());
            st.setInt(7, a.getId());
            st.executeUpdate();
            System.out.println("+ AUTHOR UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readAuthor(int authorId) {
        try {
            String req = "select * from author where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, authorId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String firstName = res.getString(2);
                System.out.println(firstName);
                String lastName = res.getString(3);
                System.out.println(lastName);
                int age = res.getInt(4);
                System.out.println(age);
                String email = res.getString(5);
                System.out.println(email);
                String description = res.getString(6);
                System.out.println(description);
                String imageUrl = res.getString(7);
                System.out.println(imageUrl);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
