package com.example.LibraryLoop.dto.openLibrary;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenLibraryDoc {

    private String title;

    private List<String> author_name;
    private List<String> author_key;

    private Integer cover_i;
    private Integer first_publish_year;

    private String key;

    private List<String> language;

    private Boolean has_fulltext;
    private Integer edition_count;

    // ⚠️ normalmente o OLID vem aqui
    private List<String> edition_key;
}