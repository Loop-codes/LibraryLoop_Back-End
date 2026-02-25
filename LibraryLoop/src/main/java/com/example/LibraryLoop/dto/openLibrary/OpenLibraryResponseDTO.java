package com.example.LibraryLoop.dto.openLibrary;

import com.example.LibraryLoop.dto.book.BookDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class OpenLibraryResponseDTO {

    private List<BookDTO> docs;
}
