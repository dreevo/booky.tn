/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.sql.Date;

/**
 *
 * @author gharbimedaziz
 */
public class Order {
    private int id;
    private Cart cart;
    private int discount;
    private String orderType;
    private Date date;
    private ShippingAddress shippingAddress;
    private Customer customer;
    private int isDone;

    public Order(int id, Cart cart, int discount, String orderType, Date date, ShippingAddress shippingAddress, Customer customer, int isDone) {
        this.id = id;
        this.cart = cart;
        this.discount = discount;
        this.orderType = orderType;
        this.date = date;
        this.shippingAddress = shippingAddress;
        this.customer = customer;
        this.isDone = isDone;
    }

    public Order(Cart cart, int discount, String orderType, Date date, ShippingAddress shippingAddress, Customer customer, int isDone) {
        this.cart = cart;
        this.discount = discount;
        this.orderType = orderType;
        this.date = date;
        this.shippingAddress = shippingAddress;
        this.customer = customer;
        this.isDone = isDone;
    }

    public Order(int id) {
        this.id = id;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }
    
    public double getCartTotal(){
        return (cart.getTotalPrice());
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getDate() {
        return date;
    }
    
    public String getDateOfOrder(){
        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public String getCustomerEmail(){
        return customer.getEmail();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getIsDone() {
        return isDone;
    }
    
    public String getIsDoneMessage(){
        if(isDone==0)return "Awaiting";
        else return "Done";
    }
    
    public void setIsDoneMessage(String x){
        if(x.equals("Done"))this.isDone=1;
        else this.isDone=0;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.discount;
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
        final Order other = (Order) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", cart=" + cart + ", discount=" + discount + ", orderType=" + orderType + ", date=" + date + ", shippingAddress=" + shippingAddress + ", customer=" + customer + ", isDone=" + isDone + '}';
    }

   
}
