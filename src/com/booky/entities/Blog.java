/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.entities;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author gharbimedaziz
 */
public class Blog {
    private int id;
    private String title;
    private String content;
    private Author author;
    private ArrayList<Comment> comments;

    public Blog(int id, String title, String content, Author author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Blog(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Blog(int id) {
        this.id = id;
    }

    public Blog(String title) {
        this.title = title;
    }

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }
    

    public Blog() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final Blog other = (Blog) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", comments=" + comments + '}';
    }
    
}
