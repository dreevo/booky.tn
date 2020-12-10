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
import javafx.collections.ObservableList;

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
            st.setString(3, e.getBeginDate());
            st.setString(4, e.getEndDate());
            st.setString(5, e.getImageUrl());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int eventId = 0;
            if (rs.next()) {
                eventId = rs.getInt(1);
            }
            // ADDING THE EVENT IN CUSTOMEREVENT TABLE
//            String req1 = "INSERT INTO customerevent (customerid,eventid) VALUES(?,?)";
//            PreparedStatement st1 = cnx.prepareStatement(req1);
//            for (int i = 0; i < e.getParticipatns().size(); i++) {
//                st1.setInt(2, eventId);
//                st1.setInt(1, e.getParticipatns().get(i).getId());
//                st1.executeUpdate();
//                System.out.println("+ CUSTOMERID " + e.getParticipatns().get(i).getId() + " AND EVENTID " + eventId + " ADDED TO customerevent TABLE");
//            }
            System.out.println("+ EVENT ADDED TO DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteEvent(String eventTitle) {
        try {
            String req = "delete from event where title=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, eventTitle);
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
            st.setString(3, e.getBeginDate());
            st.setString(4, e.getEndDate());
            st.setString(5, e.getImageUrl());
            st.setInt(6, e.getId());
            st.executeUpdate();
            // UPDATING THE CUSTOMER EVENT TABLE             
            System.out.println("+ EVENT UPDATED IN DATABASE");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readEvent(ObservableList<Event> EventList) {
        try {
            // GETTING THE EVENTOBJECT
            String req = "select id,title,description,begindate,enddate,imageurl from event";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Event ev = new Event();
                ev.setTitle(res.getString(1));
                ev.setDescription(res.getString(2));
                ev.setBeginDate(res.getString(3));
                ev.setEndDate(res.getString(4));
                ev.setImageUrl(res.getString(5));
                EventList.add(ev);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readEvents(ObservableList<Event> eventlist) {
        try {
            // GETTING THE EVENTOBJECT
            String req = "select title,description,begindate,enddate,imageurl,id from event";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Event ev = new Event();
                ev.setId(res.getInt(6));
                ev.setTitle(res.getString(1));
                ev.setDescription(res.getString(2));
                ev.setBeginDate(res.getString(3));
                ev.setEndDate(res.getString(4));
                ev.setImageUrl(res.getString(5));
                eventlist.add(ev);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void addCustomerInEvent(int customerId, int eventId){
        try{
            String req = "insert into customerevent(customerid,eventid) VALUES(?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, customerId);
            st.setInt(2, eventId);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
}
