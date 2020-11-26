/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Livre;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 21655
 */
public class ServicesLivre {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Livre l){
        try{
            String req = "INSERT INTO livre(libelle,prix,description,enstock) values('"+l.getLibelle()+"', '"+l.getPrix()+"', '"+l.getDescription()+"', '"+l.isEnStock()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livre ajouté");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void supprimer(Livre p){
        try{
            String req = "DELETE FROM livre WHERE id="+p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livre supprimée");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
