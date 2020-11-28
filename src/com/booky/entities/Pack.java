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
public class Pack {
    private int id;
    private String label;
    private double price;
    private String description;
    private String imageUrl;
    private ArrayList<Book> books = new ArrayList<>();

    public Pack(int id, String label, double price, String description, String imageUrl) {
        this.id = id;
        this.label = label;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Pack(String label, double price, String description, String imageUrl) {
        this.label = label;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
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
        final Pack other = (Pack) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pack{" + "id=" + id + ", label=" + label + ", price=" + price + ", description=" + description + ", imageUrl=" + imageUrl + ", books=" + books + '}';
    }
}
