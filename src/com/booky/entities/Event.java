/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author gharbimedaziz
 */
public class Event {
    private int id;
    private String title;
    private String description;
    private Date beginDate;
    private Date endDate;
    private String imageUrl;
    private ArrayList<Customer> participatns;

    public Event(int id, String title, String description, Date beginDate, Date endDate, String imageUrl, ArrayList<Customer> participatns) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.participatns = participatns;
    }

    public Event(String title, String description, Date beginDate, Date endDate, String imageUrl, ArrayList<Customer> participatns) {
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
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

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", title=" + title + ", description=" + description + ", beginDate=" + beginDate + ", endDate=" + endDate + ", imageUrl=" + imageUrl + ", participatns=" + participatns + '}';
    }
}
