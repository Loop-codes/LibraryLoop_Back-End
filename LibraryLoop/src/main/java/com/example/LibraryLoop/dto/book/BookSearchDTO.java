package com.example.LibraryLoop.dto.book;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookSearchDTO {

    private String title;
    private String author;
    private Integer coverId;
    private String olid;

    public BookSearchDTO() {}

    public BookSearchDTO(String title, String author, Integer coverId, String olid) {
        this.title = title;
        this.author = author;
        this.coverId = coverId;
        this.olid = olid;
    }

}