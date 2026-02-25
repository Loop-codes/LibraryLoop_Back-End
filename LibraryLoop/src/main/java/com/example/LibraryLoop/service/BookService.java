package com.example.LibraryLoop.service;

import com.example.LibraryLoop.client.OpenLibraryClient;
import com.example.LibraryLoop.Repository.userRepository;
import com.example.LibraryLoop.dto.book.BookSearchResponseDTO;
import com.example.LibraryLoop.dto.openLibrary.OpenLibraryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final OpenLibraryClient client;

    public BookService(OpenLibraryClient client, userRepository userRepository) {
        this.client = client;
    }

    public String searchBook(String title) {
        return OpenLibraryClient.searchBooks(title).toString();
    }

    public String getEditions(String workId) {
        return client.getEditions(workId);
    }

    public List<BookSearchResponseDTO> searchBooks(String title) {

        OpenLibraryResponse response = OpenLibraryClient.searchBooks(title);

        return response.getDocs()
                .stream()
                .map(doc -> new BookSearchResponseDTO(
                        doc.getTitle(),
                        doc.getCover_i(),
                        doc.getFirst_publish_year(),
                        doc.getKey(),
                        doc.getLanguage(),
                        doc.getAuthor_key()
                ))
                .toList();
    }
}