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
public class Book {
    private int id;
    private String label;
    private double price;
    private String description;
    private int isInStock;
    private String imageUrl;
    private Author author;
    private ArrayList<Category> Categories;
    private Language language;
    private int rating;

    public Book(int id, String label, double price, String description, int isInStock, String imageUrl, Author author, ArrayList<Category> Categories, Language language, int rating) {
        this.id = id;
        this.label = label;
        this.price = price;
        this.description = description;
        this.isInStock = isInStock;
        this.imageUrl = imageUrl;
        this.author = author;
        this.Categories = Categories;
        this.language = language;
        this.rating = rating;
    }

    public Book(String label, double price, String description, int isInStock, String imageUrl, Author author, ArrayList<Category> Categories, Language language, int rating) {
        this.label = label;
        this.price = price;
        this.description = description;
        this.isInStock = isInStock;
        this.imageUrl = imageUrl;
        this.author = author;
        this.Categories = Categories;
        this.language = language;
        this.rating = rating;
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

    public int getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(int isInStock) {
        this.isInStock = isInStock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ArrayList<Category> getCategories() {
        return Categories;
    }

    public void setCategories(ArrayList<Category> Categories) {
        this.Categories = Categories;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", label=" + label + ", price=" + price + ", description=" + description + ", isInStock=" + isInStock + ", imageUrl=" + imageUrl + ", author=" + author + ", Categories=" + Categories + ", language=" + language + ", rating=" + rating + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
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
        final Book other = (Book) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }
}
