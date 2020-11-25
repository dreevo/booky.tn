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
public class BonReduction {
    
    private int id;
    private int valeur;
    private String code;

    public BonReduction(int id, int valeur, String code) {
        this.id = id;
        this.valeur = valeur;
        this.code = code;
    }

    public BonReduction(int valeur, String code) {
        this.valeur = valeur;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        final BonReduction other = (BonReduction) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BonReduction{" + "id=" + id + ", valeur=" + valeur + ", code=" + code + '}';
    }
    
    
    
}
