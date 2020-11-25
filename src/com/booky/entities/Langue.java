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
public class Langue {
    private int id;
    private String nomLangue;

    public Langue(int id, String nomLangue) {
        this.id = id;
        this.nomLangue = nomLangue;
    }

    public Langue(String nomLangue) {
        this.nomLangue = nomLangue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomLangue() {
        return nomLangue;
    }

    public void setNomLangue(String nomLangue) {
        this.nomLangue = nomLangue;
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
        final Langue other = (Langue) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Langue{" + "id=" + id + ", nomLangue=" + nomLangue + '}';
    }
}
