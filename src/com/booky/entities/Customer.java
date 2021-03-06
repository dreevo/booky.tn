/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.ArrayList;

/**
 *
 * @author gharbimedaziz
 */
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String address;
    private String telephone;
    private String imageUrl;
    private String password;
    private Role role;

    public Customer(int id, String firstName, String lastName, int age, String email, String address, String telephone, String imageUrl, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public Customer(String firstName, String lastName, int age, String email, String address, String telephone, String imageUrl, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public Customer(int id) {
        this.id = id;
    }

    public Customer(String email) {
        this.email = email;
    }

    public Customer(String firstName, String lastName, int age, String email, String address, String telephone, String imageUrl, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.imageUrl = imageUrl;
        this.password = password;
        this.role = role;
    }

    public Customer(String firstName, String lastName, int age, String email, String address, String telephone, String imageUrl, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.imageUrl = imageUrl;
        this.password = password;
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.age;
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
        final Customer other = (Customer) obj;
        if (!this.email.equals(other.getEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", email=" + email + ", address=" + address + ", telephone=" + telephone + ", imageUrl=" + imageUrl + ", password=" + password + ", role=" + role + '}';
    }
}
