/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 21655
 */
public class Panier {
    private int id;
    private HashMap<Livre, Integer> produits = new HashMap<>();
    // ou bien on ajoute l'attribut total et on le calcule ou on implemente la methode calculerTotal()

    public Panier(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Livre, Integer> getProduits() {
        return produits;
    }

    public void setProduits(HashMap<Livre, Integer> produits) {
        this.produits = produits;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Panier other = (Panier) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", produits=" + produits + '}';
    }

}
