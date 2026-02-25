package com.example.LibraryLoop.controller;

import com.example.LibraryLoop.dto.book.BookSearchResponseDTO;
import com.example.LibraryLoop.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public List<BookSearchResponseDTO> search(@RequestParam String title) {
        return service.searchBooks(title);
    }

    @GetMapping("/editions/{workId}")
    public String editions(@PathVariable String workId) {
        return service.getEditions(workId);
    }
}