package com.github.kbednarz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by Kamil on 2016-06-28.
 */
@Entity
public class PostsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String author;
    private String title;
    private String content;
    private Date date;

    public PostsEntity() {
    }

    public PostsEntity(String author, String title, String content, Date date) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }
}
