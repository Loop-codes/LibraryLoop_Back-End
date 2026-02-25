package com.example.LibraryLoop.dto.list;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListSearchResponseDTO {

    private Integer start;
    private List<ListDTO> docs;
}