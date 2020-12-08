/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Customer;
import com.booky.entities.Event;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author gharbimedaziz
 */
public class EventService {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void createEvent(Event e) {
        try {
            // ADDING THE EVENT IN BOOK TABLE
            String req = "INSERT INTO event (title,description,begindate,enddate,imageurl) VALUES(?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, e.getTitle());
            st.setString(2, e.getDescription());
            st.setDate(3, e.getBeginDate());
            st.setDate(4, e.getEndDate());
            st.setString(5, e.getImageUrl());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int eventId = 0;
            if (rs.next()) {
                eventId = rs.getInt(1);
            }
            // ADDING THE EVENT IN CUSTOMEREVENT TABLE
            String req1 = "INSERT INTO customerevent (customerid,eventid) VALUES(?,?)";
            PreparedStatement st1 = cnx.prepareStatement(req1);
            for (int i = 0; i < e.getParticipatns().size(); i++) {
                st1.setInt(2, eventId);
                st1.setInt(1, e.getParticipatns().get(i).getId());
                st1.executeUpdate();
                System.out.println("+ CUSTOMERID " + e.getParticipatns().get(i).getId() + " AND EVENTID " + eventId + " ADDED TO customerevent TABLE");
            }
            System.out.println("+ EVENT ADDED TO DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteEvent(int eventId) {
        try {
            String req = "delete from event where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, eventId);
            st.executeUpdate();
            System.out.println("+ EVENT DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateEvent(Event e) {
        try {
            // UPDATING ALL COLUMNS EXCEPT THE CATEGORIES.
            String req = "update event set title=?,description=?,begindate=?,enddate=?,imageurl=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, e.getTitle());
            st.setString(2, e.getDescription());
            st.setDate(3, e.getBeginDate());
            st.setDate(4, e.getEndDate());
            st.setString(5, e.getImageUrl());
            st.setInt(6, e.getId());
            st.executeUpdate();
            // UPDATING THE CUSTOMER EVENT TABLE 
            if (!e.getParticipatns().isEmpty()) {
                //DELETING ALL CATEGORIES ASSOCIATED WITH THIS BOOK IN BOOKCATEGORY TABLE
                String req3 = "delete from customerevent where eventid=?";
                PreparedStatement st3 = cnx.prepareStatement(req3);
                st3.setInt(1, e.getId());
                st3.executeUpdate();
                for (int i = 0; i < e.getParticipatns().size(); i++) {
                    // CHECKING IF THIS CUSTOMER IS ASSOCIATED WITH THE EVENT
                    String req1 = "select * from customerevent where eventid=? and customerid=?";
                    PreparedStatement st1 = cnx.prepareStatement(req1);
                    st1.setInt(1, e.getId());
                    st1.setInt(2, e.getParticipatns().get(i).getId());
                    ResultSet res = st1.executeQuery();
                    int rowCount = 0;
                    while (res.next()) {
                        rowCount++;
                    }
                    if (rowCount == 0) {
                        String req2 = "INSERT INTO customerevent (eventid,customerid) VALUES(?,?)";
                        PreparedStatement st2 = cnx.prepareStatement(req2);
                        st2.setInt(1, e.getId());
                        st2.setInt(2, e.getParticipatns().get(i).getId());
                        st2.executeUpdate();
                    }
                }
            }
            System.out.println("+ EVENT UPDATED IN DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void readEvent(int eventId) {
        Event event = null;
        try {
            // GETTING THE EVENTOBJECT
            String req = "select * from event where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, eventId);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                String title = res.getString(2);
                System.out.println(title);
                String description = res.getString(3);
                System.out.println(description);
                Date beginDate = res.getDate(4);
                System.out.println(beginDate);
                Date endDate = res.getDate(5);
                System.out.println(endDate);
                String imageUrl = res.getString(6);
                System.out.println(imageUrl);
            }
            // GETTING CUSTOMERS IDS OF THE EVENT
            String req1 = "select * from customerevent where eventid=?";
            PreparedStatement st1 = cnx.prepareStatement(req1);
            st1.setInt(1, eventId);
            ResultSet res1 = st1.executeQuery();
            ArrayList<Integer> customersIds = new ArrayList<>();
            while (res1.next()) {
                customersIds.add(res1.getInt(1));
            }
            //System.out.println(customersIds);
            // GETTING THE LIST OF CUSTOMERS
            ArrayList<Customer> customers = new ArrayList<>();
            for (int i = 0; i < customersIds.size(); i++) {
                String req2 = "select * from customer where id=?";
                PreparedStatement st2 = cnx.prepareStatement(req2);
                st2.setInt(1, customersIds.get(i));
                ResultSet res2 = st2.executeQuery();
                while (res2.next()) {
                    customers.add(new Customer(customersIds.get(i),res2.getString(2), res2.getString(3), res2.getInt(4), res2.getString(5), res2.getString(6), res2.getString(7),res2.getString(8), null));
                }
            }
            System.out.println(customers);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
