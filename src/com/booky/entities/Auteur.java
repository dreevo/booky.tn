/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

/**
 *
 * @author 21655
 */
public class Auteur {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String description;
    private String imageUrl;

    public Auteur(int id, String nom, String prenom, int age, String description) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.description = description;
    }

    public Auteur(String nom, String prenom, int age, String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "Auteur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", description=" + description + ", imageUrl=" + imageUrl + '}';
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
        final Auteur other = (Auteur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
