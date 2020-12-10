/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Comment;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author J.Maroua
 */
public class CommentService {
    Connection cnx = DataSource.getInstance().getCnx();
    public void createComment(Comment c) {
        try {
            // ADDING THE COMMENT IN COMMENT TABLE
            String req = "INSERT INTO comment (description,customerid,blogid) VALUES(?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, c.getDescription());
            st.setInt(2, c.getCustomer().getId());
            st.setInt(3, c.getBlog().getId());
            st.executeUpdate();
            System.out.println("+ COMMENT ADDED TO DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteComment(int commentId) {
        try {
            String req = "delete from comment where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, commentId);
            st.executeUpdate();
            System.out.println("+ COMMENT DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void updateComment(Comment c) {
        try {
            String req = "update comment set description=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, c.getDescription());
            st.setInt(2, c.getId());
            st.executeUpdate();
            System.out.println("+ Pack UPDATED IN DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void readComment(int commentId) {
        try {
            // GETTING THE COMMENT OBJECT
            String req = "select c.description, cu.firstname,cu.lastname from comment c join customer cu on c.customerid=cu.id and c.id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, commentId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String firstName = res.getString(2);
                String lastName = res.getString(3);
                System.out.println(lastName + " " + firstName);
                 String description = res.getString(1);
                System.out.println(description);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
