package com.example.LibraryLoop.dto.book;

public class PageResponse {

    private int page;
    private int totalPages;
    private String content;

    public PageResponse(int page, int totalPages, String content) {
        this.page = page;
        this.totalPages = totalPages;
        this.content = content;
    }

    public int getPage() { return page; }
    public int getTotalPages() { return totalPages; }
    public String getContent() { return content; }
}
