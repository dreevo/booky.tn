/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.Objects;

/**
 *
 * @author gharbimedaziz
 */
public class ShippingAddress {
    private int id;
    private String address;
    private String city;
    private String zipcode;
    private Customer customer;
    private Order order;

    public ShippingAddress(int id, String address, String city, String zipcode, Customer customer, Order order) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.customer = customer;
        this.order = order;
    }

    public ShippingAddress(String address, String city, String zipcode, Customer customer, Order order) {
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.customer = customer;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.zipcode);
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
        final ShippingAddress other = (ShippingAddress) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
}
