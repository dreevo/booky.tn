/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

/**
 *
 * @author gharbimedaziz
 */
public class Comment {
    private int id;
    private String description;
    private Customer customer;
    private Blog blog;

    public Comment(int id, String description, Customer customer) {
        this.id = id;
        this.description = description;
        this.customer = customer;
    }

    public Comment(String description, Customer customer) {
        this.description = description;
        this.customer = customer;
    }

    public Comment() {
    }

    public Comment(int id, String description, Customer customer, Blog blog) {
        this.id = id;
        this.description = description;
        this.customer = customer;
        this.blog = blog;
    }

    public Comment(String description, Customer customer, Blog blog) {
        this.description = description;
        this.customer = customer;
        this.blog = blog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        final Comment other = (Comment) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", description=" + description + ", customer=" + customer + '}';
    }
}
