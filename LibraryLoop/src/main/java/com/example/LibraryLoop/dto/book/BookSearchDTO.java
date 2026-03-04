package com.example.LibraryLoop.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookSearchDTO {

    private String olid;
    private String title;
    private List<String> authorName;
    private List<String> authorKey;
    private Integer coverId;
    private Integer firstPublishYear;
    private List<String> language;
    private Boolean hasFullText;
    private Integer editionCount;

}