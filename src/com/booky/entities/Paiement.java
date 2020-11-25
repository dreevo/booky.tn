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
public class Paiement {
    private int id;
    private boolean validite;
    private BonReduction bon;
    private String modePaiement;

    public Paiement(int id, boolean validite, String modePaiement) {
        this.id = id;
        this.validite = validite;
        this.modePaiement = modePaiement;
    }

    public Paiement(boolean validite, String modePaiement) {
        this.validite = validite;
        this.modePaiement = modePaiement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isValidite() {
        return validite;
    }

    public void setValidite(boolean validite) {
        this.validite = validite;
    }

    public BonReduction getBon() {
        return bon;
    }

    public void setBon(BonReduction bon) {
        this.bon = bon;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
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
        final Paiement other = (Paiement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paiement{" + "id=" + id + ", validite=" + validite + ", bon=" + bon + ", modePaiement=" + modePaiement + '}';
    }

    
}
