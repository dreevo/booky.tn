/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Blog;
import com.booky.entities.Comment;
import com.booky.entities.Customer;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gharbimedaziz
 */
public class BlogService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createBlog(Blog b) {
        try {
            // ADDING THE BLOG ITEM IN CARTITEM TABLE
            String req = "INSERT INTO blog (title,content,authorid) VALUES(?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, b.getTitle());
            st.setString(2, b.getContent());
            st.setInt(3, b.getAuthor().getId());
            st.executeUpdate();
            System.out.println("+ BLOG ADDED TO DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteCartItem(int blogId) {
        try {
            String req = "delete from blog where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, blogId);
            st.executeUpdate();
            System.out.println("+ BLOG DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBlog(Blog b) {
        try {
            String req = "update blog set title=?,content=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, b.getTitle());
            st.setString(2, b.getContent());
            st.setInt(3, b.getId());
            st.executeUpdate();
            System.out.println("+ CART ITEM UPDATED IN DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void readBlog(int blogId) {
        try {
            // GETTING THE PACK OBJECT
            String req = "select b.title, b.content,c.description,c.customerid,c.id from blog b join comment c on b.id=c.blogid and b.id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, blogId);
            ResultSet res = st.executeQuery();
            ArrayList<Comment> comments = new ArrayList<>();
            String title,content,description;
            while (res.next()) {
                title = res.getString(1);
                content = res.getString(2);
                description = res.getString(3);
                int commentId = res.getInt(5);
                String req1 = "select cu.firstname,cu.lastname from comment c join customer cu on c.customerid=cu.id and c.id=? and cu.id=?";
                PreparedStatement st1 = cnx.prepareStatement(req1);
                st1.setInt(1, commentId);
                st1.setInt(2, res.getInt(4));
                ResultSet res1 = st1.executeQuery();
                while(res1.next()){
                    String customerFirstName = res1.getString(1);
                    String customerLastName = res1.getString(2);
                    Customer c = new Customer(customerFirstName, customerLastName);
                    comments.add(new Comment(description, c));
                }
            }
            System.out.println(comments);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
