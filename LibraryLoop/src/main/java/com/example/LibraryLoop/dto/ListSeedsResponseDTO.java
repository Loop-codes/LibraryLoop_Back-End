package com.example.LibraryLoop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListSeedsResponseDTO {

    private Integer size;
    private List<SeedDTO> entries;
}