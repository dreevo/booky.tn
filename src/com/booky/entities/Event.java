/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author gharbimedaziz
 */
public class Event {
    private int id;
    private String title;
    private String description;
    private Calendar beginDate;
    private Calendar endDate;
    private String imageUrl;
    private ArrayList<Customer> participatns;

    public Event(int id, String title, String description, Calendar beginDate, Calendar endDate, String imageUrl, ArrayList<Customer> participatns) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.participatns = participatns;
    }

    public Event(String title, String description, Calendar beginDate, Calendar endDate, String imageUrl, ArrayList<Customer> participatns) {
        this.title = title;
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;
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

    public Calendar getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Calendar beginDate) {
        this.beginDate = beginDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
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
    public String toString() {
        return "Event{" + "id=" + id + ", title=" + title + ", description=" + description + ", beginDate=" + beginDate + ", endDate=" + endDate + ", imageUrl=" + imageUrl + ", participatns=" + participatns + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }
}
