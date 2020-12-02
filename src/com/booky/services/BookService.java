/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Author;
import com.booky.entities.Book;
import com.booky.entities.Category;
import com.booky.entities.Language;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author gharbimedaziz
 */
public class BookService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createBook(Book b) {
        try {
            // ADDING THE BOOK IN BOOK TABLE
            String req = "INSERT INTO book (label,price,description,isinstock,rating,imageurl,languageid,authorid) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, b.getLabel());
            st.setDouble(2, b.getPrice());
            st.setString(3, b.getDescription());
            st.setInt(4, b.getIsInStock());
            st.setInt(5, b.getRating());
            st.setString(6, b.getImageUrl());
            st.setInt(7, b.getLanguage().getId());
            st.setInt(8, b.getAuthor().getId());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int bookId = 0;
            if (rs.next()) {
                bookId = rs.getInt(1);
            }
            // ADDING THE BOOK IN BOOKCATEGORY TABLE
            String req1 = "INSERT INTO bookcategory (bookid,categoryid) VALUES(?,?)";
            PreparedStatement st1 = cnx.prepareStatement(req1);
            for (int i = 0; i < b.getCategories().size(); i++) {
                st1.setInt(1, bookId);
                st1.setInt(2, b.getCategories().get(i).getId());
                st1.executeUpdate();
                System.out.println("+ CATEGORYID " + b.getCategories().get(i).getId() + " AND BOOKID " + bookId + " ADDED TO bookCategory TABLE");
            }
            System.out.println("+ BOOK ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook(int bookId) {
        try {
            String req = "delete from book where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, bookId);
            st.executeUpdate();
            System.out.println("+ BOOK DELETED FROM DATABASE");
            String req1 = "delete from bookcategory where bookid=?";
            PreparedStatement st1 = cnx.prepareStatement(req1);
            st1.setInt(1, bookId);
            st1.executeUpdate();
            System.out.println("+ BOOK DELETED FROM BOOKCATEGORY TABLE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBook(Book b) {
        try {
            // UPDATING ALL COLUMNS EXCEPT THE CATEGORIES.
            String req = "update book set label=?,price=?,description=?,isinstock=?,imageurl=?,authorid=?,languageid=?,rating=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, b.getLabel());
            st.setDouble(2, b.getPrice());
            st.setString(3, b.getDescription());
            st.setInt(4, b.getIsInStock());
            st.setString(5, b.getImageUrl());
            st.setInt(6, b.getAuthor().getId());
            st.setInt(7, b.getLanguage().getId());
            st.setInt(8, b.getRating());
            st.setInt(9, b.getId());
            st.executeUpdate();
            // UPDATING THE BOOK CATEGORY TABLE 
            if (!b.getCategories().isEmpty()) {
                //DELETING ALL CATEGORIES ASSOCIATED WITH THIS BOOK IN BOOKCATEGORY TABLE
                String req3 = "delete from bookcategory where bookid=?";
                PreparedStatement st3 = cnx.prepareStatement(req3);
                st3.setInt(1, b.getId());
                st3.executeUpdate();
                for (int i = 0; i < b.getCategories().size(); i++) {
                    // CHECKING IF THIS CATEGORY IS ASSOCIATED WITH THE BOOK
                    String req1 = "select * from bookcategory where bookid=? and categoryid=?";
                    PreparedStatement st1 = cnx.prepareStatement(req1);
                    st1.setInt(1, b.getId());
                    st1.setInt(2, b.getCategories().get(i).getId());
                    ResultSet res = st1.executeQuery();
                    int rowCount = 0;
                    while (res.next()) {
                        rowCount++;
                    }
                    if (rowCount == 0) {
                        String req2 = "INSERT INTO bookcategory (bookid,categoryid) VALUES(?,?)";
                        PreparedStatement st2 = cnx.prepareStatement(req2);
                        st2.setInt(1, b.getId());
                        st2.setInt(2, b.getCategories().get(i).getId());
                        st2.executeUpdate();
                    }
                }
            }
            System.out.println("+ BOOK UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Book readBook(int bookId) {
        Book b = new Book();
        try {
            // GETTING THE BOOK OBJECT
            String req = "select b.label,b.price,b.description,b.isinstock,b.rating,b.imageurl,l.languagename,l.id from book b join language l on l.id=b.languageid and b.id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, bookId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String label = res.getString(1);
                b.setLabel(label);;
                double price = res.getInt(2);
                b.setPrice(price);
                String description = res.getString(3);
                b.setDescription(description);
                int isInStock = res.getInt(4);
                b.setIsInStock(isInStock);
                int rating = res.getInt(5);
                b.setRating(rating);
                String imageUrl = res.getString(6);
                b.setImageUrl(imageUrl);
                String languageName = res.getString(7);
                String languageId = res.getString(8);
                b.setLanguage(new Language(Integer.parseInt(languageId), languageName));
                // GETTING THE LIST OF CATEGORIES OF THAT BOOK
                String req1 = "select bc.categoryid,c.categoryname from bookcategory bc join category c on c.id=bc.categoryid and bc.bookid=?";
                PreparedStatement st1 = cnx.prepareStatement(req1);
                st1.setInt(1, bookId);
                ResultSet res1 = st1.executeQuery();
                ArrayList<Category> categs = new ArrayList<>();
                while (res1.next()) {
                    categs.add(new Category(res1.getInt(1),res1.getString(2)));
                }
                b.setCategories(categs);
                // GETTING THE AUTHOR OF THAT BOOK
                String req2 = "select a.id,a.firstname,lastname from book b join author a on a.id=b.authorid and b.id=?";
                PreparedStatement st2 = cnx.prepareStatement(req2);
                st2.setInt(1, bookId);
                ResultSet res2 = st2.executeQuery();
                while (res2.next()) {
                    b.setAuthor(new Author(res2.getInt(1), res2.getString(2), res2.getString(3)));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return b;
    }

    public void readBooks(ObservableList<Book> bookList) {
        try {
            String req = "select b.label,b.price,b.description,b.isinstock,b.rating,b.imageurl,l.languagename,l.id,b.id from book b join language l on l.id=b.languageid";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            Book b;
            while (res.next()) {
                b = new Book();
                b.setLabel(res.getString(1));
                b.setPrice(res.getDouble(2));
                b.setDescription(res.getString(3));
                b.setIsInStock(res.getInt(4));
                b.setRating(res.getInt(5));
                b.setImageUrl(res.getString(6));
                String languageName = res.getString(7);
                String languageId = res.getString(8);
                b.setLanguage(new Language(Integer.parseInt(languageId), languageName));
                int bookId = res.getInt(9);
                String req2 = "select a.id,a.firstname,lastname from book b join author a on a.id=b.authorid and b.id=?";
                PreparedStatement st2 = cnx.prepareStatement(req2);
                st2.setInt(1, bookId);
                ResultSet res2 = st2.executeQuery();
                while (res2.next()) {
                    b.setAuthor(new Author(res2.getInt(1), res2.getString(2), res2.getString(3)));
                }
                String req3 = "select c.id,c.categoryname from bookcategory bc join category c on bc.categoryid=c.id and bc.bookid=?";
                PreparedStatement st3 = cnx.prepareStatement(req3);
                st3.setInt(1, bookId);
                ResultSet res3 = st3.executeQuery();
                ArrayList<Category> categs = new ArrayList<>();
                while (res3.next()) {
                    categs.add(new Category(res3.getInt(1), res3.getString(2)));
                }
                b.setCategories(categs);
                b.setId(bookId);
                bookList.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
