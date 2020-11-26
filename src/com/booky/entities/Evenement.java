/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.Calendar;

/**
 *
 * @author 21655
 */
public class Evenement {
    private int id;
    private String intitule;
    private String description;
    private Calendar date;
    private String imageUrl;

    public Evenement(int id, String intitule, String description, Calendar date) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;
        this.date = date;
    }

    public Evenement(String intitule, String description, Calendar date) {
        this.intitule = intitule;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", intitule=" + intitule + ", description=" + description + ", date=" + date + ", imageUrl=" + imageUrl + '}';
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
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
