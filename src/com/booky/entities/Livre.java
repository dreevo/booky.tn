/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

/**
 *
 * @author 21655
 */
public class Livre {
    private int id;
    private String libelle;
    private double prix;
    private String description;
    private int enStock;
    private String imageUrl;

    public Livre(int id, String libelle, double prix, String description, int enStock) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.enStock = enStock;
    }

    public Livre(String libelle, double prix, String description, int enStock) {
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.enStock = enStock;
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

    public int isEnStock() {
        return enStock;
    }

    public void setEnStock(int enStock) {
        this.enStock = enStock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Livre{" + "id=" + id + ", libelle=" + libelle + ", prix=" + prix + ", description=" + description + ", enStock=" + enStock + ", imageUrl=" + imageUrl + '}';
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
        final Livre other = (Livre) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
      
}
