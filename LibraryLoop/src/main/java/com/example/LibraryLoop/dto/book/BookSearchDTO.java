package com.example.LibraryLoop.dto.book;

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

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getCoverId() {
        return coverId;
    }

    public String getOlid() {
        return olid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }

    public void setOlid(String olid) {
        this.olid = olid;
    }
}