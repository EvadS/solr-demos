package com.se.article;

import org.apache.solr.client.solrj.beans.Field;

public class Article {
    @Field
    private String id;

    @Field
    private String category;

    @Field
    private String title;

    @Field
    private String author;

    @Field
    private boolean published;

    public Article() {
    }

    public Article(String id, String category, String title, String author, boolean published) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
