/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Customer;
import com.booky.entities.Exchange;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Malek
 */
public class ExchangeService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void createExchange(Exchange ex) {
        try {
            String req = "INSERT INTO exchange (customeridR,exchangeDescription,exchangeStatus) VALUES(?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, ex.getIdCustomerR());
            st.setString(2, ex.getExchangeDescription());
            st.setString(3, ex.getExchangeStatus());
            st.executeUpdate();
            System.out.println("+ EXCHANGE ADDED TO DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteExchange(int exchangeId) {
        try {
            String req = "delete from exchange where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, exchangeId);
            st.executeUpdate();
            System.out.println("+ EXCHANGE DELETED FROM DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCharity(Exchange ex) {
        try {
            String req = "update exchange set customeridR=?,exchangeDescription=?,exchangeStatus=? where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, ex.getIdCustomerR());
            st.setString(2, ex.getExchangeDescription());
            st.setString(3, ex.getExchangeStatus());
            st.executeUpdate();
            System.out.println("+ EXCHANGE UPDATED IN DATABASE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Exchange readExchange(int idExchange) {
        Exchange exchange = null;
        try {
            // READING ALL THE EXCHANGE TABLE DATA
            String req = "select * from exchange where id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idExchange);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                int idCustomerR = res.getInt(2);
                exchange.setIdCustomerR(idCustomerR);
                System.out.println(idCustomerR);
                String exchangeDescription = res.getString(3);
                exchange.setExchangeDescription(exchangeDescription);
                System.out.println(exchangeDescription);
                String exchangeStatus = res.getString(4);
                exchange.setExchangeStatus(exchangeStatus);
                System.out.println(exchangeStatus);
            }
            // GETTING CUSTOMERS IDS OF THE EXCHANGE
            String req1 = "select * from customer where exchangeId=?";
            PreparedStatement st1 = cnx.prepareStatement(req1);
            st1.setInt(1, idExchange);
            ResultSet res1 = st1.executeQuery();
            ArrayList<Integer> customerIds = new ArrayList<>();
            while (res1.next()) {
                customerIds.add(res1.getInt(1));
            }
            System.out.println(customerIds);
            // GETTING THE LIST OF CUSTOMERS
            ArrayList<Customer> customers = new ArrayList<>();
            for (int i = 0; i < customerIds.size(); i++) {
                String req2 = "select * from customer where id=?";
                PreparedStatement st2 = cnx.prepareStatement(req2);
                st2.setInt(1, customerIds.get(i));
                ResultSet res2 = st2.executeQuery();
                while (res2.next()) {
                    customers.add(new Customer(customerIds.get(i), res2.getString(2), res2.getString(3), res2.getString(4), res2.getString(5)));
                }
            }
            System.out.println(customers);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exchange;
    }

    public void readExchanges(ObservableList<Exchange> exchangeList) {
        try {
            String req = "select customeridR,exchangeDescription,exchangeStatus from exchange";
            PreparedStatement st = cnx.prepareStatement(req);

            ResultSet res = st.executeQuery();
            while (res.next()) {
                Exchange exchange = new Exchange();
                exchange.setIdCustomerR(res.getInt(1));
                exchange.setExchangeDescription(res.getString(2));
                exchange.setExchangeStatus(res.getString(3));

                exchangeList.add(exchange);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void readCustomerExchange(ObservableList<Customer> customerList) {
        try {
            String req = "select c.id,c.firstname,c.lastname,c.email,c.telephone from customer c join exchange ex on ex.customeridR=c.id";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Customer cReciever = new Customer();
                cReciever.setId(res.getInt(1));
                cReciever.setFirstName(res.getString(2));
                cReciever.setLastName(res.getString(3));
                cReciever.setEmail(res.getString(4));
                cReciever.setTelephone(res.getString(5));
            
                ArrayList<Exchange> exchanges = new ArrayList<>();
                String req2 = "select * from exchange where customeridR=?";
                PreparedStatement st2 = cnx.prepareStatement(req2);
                st2.setInt(1, cReciever.getId());
                ResultSet res2 = st2.executeQuery();
                while (res2.next()) {
                    exchanges.add(new Exchange(res2.getInt(2), res2.getString(3),res2.getBoolean(4)));
                }
                cReciever.setExchanges(exchanges);

                customerList.add(cReciever);
            
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Customer acceptExchange(Exchange ex){
      Customer cSender = null;
      if (ex.getExchangeStatus()=="done"){
          System.out.println("this offer of exchange no longer stands");
      }
      else{
    try {
            String req = "select c.id,c.firstname,c.lastname,c.email,c.telephone from customer c join exchange ex on ex.customeridR=c.id";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                cSender = new Customer();
                cSender.setId(res.getInt(1));
                cSender.setFirstName(res.getString(2));
                cSender.setLastName(res.getString(3));
                cSender.setEmail(res.getString(4));
                cSender.setTelephone(res.getString(5));
    }
    }catch (SQLException e){
        System.out.println(e.getMessage());
    }
      }
        return cSender;


}
}
