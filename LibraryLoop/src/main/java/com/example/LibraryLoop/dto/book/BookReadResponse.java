package com.example.LibraryLoop.dto.book;

public class BookReadResponse {

    private Long id;
    private String content;

    public BookReadResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}