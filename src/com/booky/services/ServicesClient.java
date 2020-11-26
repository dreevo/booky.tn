/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Client;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 21655
 */
public class ServicesClient {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void AjouterClient(Client c){
        try{
            String req = "INSERT INTO client (nom, prenom, age, email, adresse,imageurl, telephone) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, c.getNom());
            st.setString(2, c.getPrenom());
            st.setInt(3, c.getAge());
            st.setString(4, c.getEmail());
            st.setString(5, c.getAdresse());
            st.setString(6, null);
            st.setString(7, c.getTelephone());
            st.executeUpdate();
            System.out.println("Client ajouté");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
