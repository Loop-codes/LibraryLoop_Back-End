package com.example.LibraryLoop.dto.list;

import com.example.LibraryLoop.dto.seed.SeedDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListSeedsResponseDTO {

    private Integer size;
    private List<SeedDTO> entries;
}