package com.example.LibraryLoop.dto.openLibrary;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenLibraryResponse {

    private int numFound;
    private int start;
    private boolean numFoundExact;
    private List<OpenLibraryDoc> docs;

}