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
public class Donation {
    private int id;
    private String message;
    private double amount;
    private Customer customer;
    private Charity charity;

    public Donation(int id, String message, double amount, Customer customer) {
        this.id = id;
        this.message = message;
        this.amount = amount;
        this.customer = customer;
    }

    public Donation(String message, double amount, Customer customer) {
        this.message = message;
        this.amount = amount;
        this.customer = customer;
    }

    public Donation(String message, double amount, Customer customer, Charity charity) {
        this.message = message;
        this.amount = amount;
        this.customer = customer;
        this.charity = charity;
    }

    public Donation(int id, String message, double amount, Customer customer, Charity charity) {
        this.id = id;
        this.message = message;
        this.amount = amount;
        this.customer = customer;
        this.charity = charity;
    }

    public Donation(int id, String message, double amount) {
        this.id = id;
        this.message = message;
        this.amount = amount;
    }

    public Donation() {
    }
    
    public Donation(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    @Override
    public String toString() {
        return "Donation{" + "id=" + id + ", message=" + message + ", amount=" + amount + ", customer=" + customer + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
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
        final Donation other = (Donation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
