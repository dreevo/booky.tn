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
    private int zipcode;
    private Customer customer;
    private Order order;
    private String telephone;

    public ShippingAddress(int id, String address, String city, int zipcode, Customer customer, Order order) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.customer = customer;
        this.order = order;
    }

    public ShippingAddress(String address, String city, int zipcode, Customer customer, Order order, String telephone) {
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.customer = customer;
        this.order = order;
        this.telephone = telephone;
    }

    public ShippingAddress(String address, String city, int zipcode, Customer customer, Order order) {
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.customer = customer;
        this.order = order;
    }

    public ShippingAddress() {
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

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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

    @Override
    public String toString() {
        return "ShippingAddress{" + "id=" + id + ", address=" + address + ", city=" + city + ", zipcode=" + zipcode + ", customer=" + customer + ", order=" + order + '}';
    }

    
    
}
