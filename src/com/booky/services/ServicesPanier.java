/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.services;

import com.booky.entities.Client;
import com.booky.entities.Livre;
import com.booky.entities.Panier;
import com.booky.utils.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author 21655
 */
public class ServicesPanier {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterPdtAuPanier(Livre l, int clientId, Panier p){
        boolean existe = p.getProduits().containsKey(l);
        if(existe){
            int quantite = p.getProduits().get(l);
            p.getProduits().put(l, quantite+1);
        }else{
            p.getProduits().put(l, 1);
        }
    }
    
    public void retirerPdtDuPanier(){
        
    }
}
