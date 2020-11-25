/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.ArrayList;

/**
 *
 * @author 21655
 */
public class Pack {
    private int id;
    private String libelle;
    private double prix;
    private String description;
    private ArrayList<Livre> livres = new ArrayList<>();

    public Pack(int id, String libelle, double prix, String description) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
    }

    public Pack(String libelle, double prix, String description) {
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Livre> getLivres() {
        return livres;
    }

    public void setLivres(ArrayList<Livre> livres) {
        this.livres = livres;
    }

    @Override
    public String toString() {
        return "Pack{" + "id=" + id + ", libelle=" + libelle + ", prix=" + prix + ", description=" + description + ", livres=" + livres + '}';
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
        final Pack other = (Pack) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
