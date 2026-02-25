package com.example.LibraryLoop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListsResponseDTO {

    // Quantidade total de listas do usuário
    private Integer size;

    // As listas em si
    private List<ListDTO> entries;
}