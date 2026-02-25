package com.example.LibraryLoop.dto;


import lombok.Getter;
import lombok.Setter;

public class EditionDTO {

    @Getter
    private String key;
    private String title;
    private String[] isbn_10;

    @Setter
    @Getter
    private String ocaid; // ID do Internet Archive

}
