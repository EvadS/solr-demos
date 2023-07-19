package com.se.sample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Book {
    private String id;
    private String isbn;
    private String author;
    private String title;
    private String publisher;
    private String genre;
    private String preview;
}
