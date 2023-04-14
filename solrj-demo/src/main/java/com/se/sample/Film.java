package com.se.sample;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

public class Film {
    @Field
    private String id;

    @Field
    private List<String> name;

    @Field("directed_by")
    private List<String> directedBy;

    @Field("initial_release_date")
    private List<String> initialReleaseDate;

    @Field
    private List<String> genre;

    public Film() {
    }

    public Film(String id, List<String> name, List<String> directedBy, List<String> initialReleaseDate, List<String> genre) {
        this.id = id;
        this.name = name;
        this.directedBy = directedBy;
        this.initialReleaseDate = initialReleaseDate;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(List<String> directedBy) {
        this.directedBy = directedBy;
    }

    public List<String> getInitialReleaseDate() {
        return initialReleaseDate;
    }

    public void setInitialReleaseDate(List<String> initialReleaseDate) {
        this.initialReleaseDate = initialReleaseDate;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
}
