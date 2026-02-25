package com.example.LibraryLoop.service;

import com.example.LibraryLoop.client.OpenLibraryClient;
import com.example.LibraryLoop.Repository.userRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final OpenLibraryClient client;
    private final userRepository userRepository;

    public BookService(OpenLibraryClient client, userRepository userRepository) {
        this.client = client;
        this.userRepository = userRepository;
    }

    public String searchBook(String title) {
        return client.searchBooks(title);
    }

    public String getEditions(String workId) {
        return client.getEditions(workId);
    }
}