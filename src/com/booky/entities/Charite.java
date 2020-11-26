/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.HashMap;

/**
 *
 * @author 21655
 */
public class Charite {
    private int id;
    private String title;
    private String description;
    private HashMap<Client, Double> dons = new HashMap<>();
    private String imageUrl;

    public Charite(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Charite(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public HashMap<Client, Double> getDons() {
        return dons;
    }

    public void setDons(HashMap<Client, Double> dons) {
        this.dons = dons;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Charite{" + "id=" + id + ", title=" + title + ", description=" + description + ", dons=" + dons + ", imageUrl=" + imageUrl + '}';
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
        final Charite other = (Charite) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
