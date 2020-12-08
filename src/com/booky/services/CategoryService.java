/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Category;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gharbimedaziz
 */
public class CategoryService {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void createCategory (Category c){
    try {
        String req = "INSERT INTO category (categoryName) VALUES(?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, c.getCategoryName());
        st.executeUpdate();
        System.out.println("+ CATEGORY ADDED TO DATABASE");
         } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
     public void deleteCategory (int categoryId) {
        try {
            String req = "delete from category where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, categoryId);
            st.executeUpdate();
            System.out.println("+ CATEGORY DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     
      public void updateCategory (Category c){
      try {
            String req = "update category set categoryName=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, c.getCategoryName());
            st.setInt(2, c.getId());
            st.executeUpdate();
            System.out.println("+ CATEGORY UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
      
       public void readCategory(int categoryId) {
        try {
            String req = "select c.categoryName from category c where c.id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, categoryId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String categoryName = res.getString(1);
                System.out.println(categoryName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
