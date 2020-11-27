/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author gharbimedaziz
 */
public class Event {
    private int id;
    private String title;
    private String description;
    private Calendar date;
    private String imageUrl;
    private ArrayList<Customer> participatns;

    public Event(int id, String title, String description, Calendar date, String imageUrl, ArrayList<Customer> participatns) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.imageUrl = imageUrl;
        this.participatns = participatns;
    }

    public Event(String title, String description, Calendar date, String imageUrl, ArrayList<Customer> participatns) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.imageUrl = imageUrl;
        this.participatns = participatns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<Customer> getParticipatns() {
        return participatns;
    }

    public void setParticipatns(ArrayList<Customer> participatns) {
        this.participatns = participatns;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
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
        final Event other = (Event) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", imageUrl=" + imageUrl + ", participatns=" + participatns + '}';
    }

}
