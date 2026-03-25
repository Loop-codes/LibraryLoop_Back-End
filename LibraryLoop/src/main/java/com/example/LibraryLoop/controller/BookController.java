package com.example.LibraryLoop.controller;

import com.example.LibraryLoop.dto.book.BookReadResponse;
import com.example.LibraryLoop.dto.book.BookSearchDTO;
import com.example.LibraryLoop.dto.book.PageResponse;
import com.example.LibraryLoop.dto.read.ReadLinkDTO;
import com.example.LibraryLoop.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 🔎 BUSCAR LIVROS
    @GetMapping("/search")
    public List<BookSearchDTO> searchBooks(
            @RequestParam String title,
            @RequestParam(defaultValue = "20") int limit
    ) {
        return bookService.searchBooks(title, limit);
    }

    // 📖 LER LIVRO COMPLETO
    @GetMapping("/{id}/read")
    public ResponseEntity<BookReadResponse> readBook(@PathVariable Long id) {

        String text = bookService.getFullBook(id);

        return ResponseEntity.ok(new BookReadResponse(id, text));
    }

    // 📄 PAGINAÇÃO
    @GetMapping("/{id}/pages/{page}")
     public ResponseEntity<PageResponse> getBookPage(
            @PathVariable Long id,
            @PathVariable int page
    ) {

        List<String> pages = bookService.getBookPages(id);

        if (page < 0 || page >= pages.size()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                new PageResponse(page, pages.size(), pages.get(page))
        );
<<<<<<< HEAD
    }   

    // 🔎 buscar livros
    @GetMapping(value = "/search")
    public List<BookSearchDTO> searchBooks(
            @RequestParam String title,
            @RequestParam(defaultValue = "20") int limit) {

        return service.searchBooks(title, limit);
=======
    }

    // 🔗 LINK EXTERNO (OPCIONAL)
    @GetMapping("/{id}/link")
    public ReadLinkDTO getReadLink(@PathVariable String id) {
        return bookService.getReadLink(id);
>>>>>>> origin/main
    }
}