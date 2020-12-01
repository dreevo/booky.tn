/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author gharbimedaziz
 */
public class Charity {
    private int id;
    private String title;
    private String description;
    private ArrayList<Donation> donations;
    private String imageUrl;
    private Book book;

    public Charity(int id, String title, String description, ArrayList<Donation> donations, String imageUrl, Book book) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.donations = donations;
        this.imageUrl = imageUrl;
        this.book = book;
    }

    public Charity(String title, String description, ArrayList<Donation> donations, String imageUrl, Book book) {
        this.title = title;
        this.description = description;
        this.donations = donations;
        this.imageUrl = imageUrl;
        this.book = book;
    }

    public Charity(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Charity(int id, String title, String description, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
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

    public ArrayList<Donation> getDonations() {
        return donations;
    }

    public void setDonations(ArrayList<Donation> donations) {
        this.donations = donations;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        return hash;
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
        final Charity other = (Charity) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Charity{" + "id=" + id + ", title=" + title + ", description=" + description + ", donations=" + donations + ", imageUrl=" + imageUrl + ", book=" + book + '}';
    }

    
}
