package com.example.LibraryLoop.service;

import com.example.LibraryLoop.client.GutendexClient;
import com.example.LibraryLoop.dto.book.BookSearchDTO;
import com.example.LibraryLoop.dto.gutendex.GutendexAuthor;
import com.example.LibraryLoop.dto.gutendex.GutendexBookResponse;
import com.example.LibraryLoop.dto.read.ReadLinkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final GutendexClient gutendexClient;
    private final RestTemplate restTemplate;

    // 🔎 BUSCA LIVROS
    @Cacheable(value = "books", key = "#title + '-' + #limit")
    public List<BookSearchDTO> searchBooks(String title, int limit) {

        return gutendexClient.searchBooks(title)
                .getResults()
                .stream()
                .limit(limit)
                .map(book -> {

                    String cover = null;

                    if (book.getFormats() != null) {
                        cover = book.getFormats()
                                .entrySet()
                                .stream()
                                .filter(f -> f.getKey().contains("image"))
                                .map(Map.Entry::getValue)
                                .findFirst()
                                .orElse(null);
                    }

                    boolean readable = book.getFormats() != null &&
                            book.getFormats().keySet().stream()
                                    .anyMatch(key -> key.contains("text/plain"));

                    return new BookSearchDTO(
                            String.valueOf(book.getId()),
                            book.getTitle(),
                            book.getAuthors()
                                    .stream()
                                    .map(GutendexAuthor::getName)
                                    .toList(),
                            cover,
                            null,
                            null,
                            null,
                            true,
                            null,
                            readable,
                            "GUTENDEX"
                    );
                })
                .toList();
    }

    // =========================
// 📖 TEXTO COMPLETO (CACHEADO)
// =========================
    @Cacheable(value = "bookText", key = "#id")
    public String getFullBook(Long id) {

        String url = "https://gutendex.com/books/" + id;

        GutendexBookResponse response =
                restTemplate.getForObject(url, GutendexBookResponse.class);

        if (response == null || response.getFormats() == null) {
            return "Livro não encontrado";
        }

        String textUrl = response.getFormats()
                .entrySet()
                .stream()
                .filter(f -> f.getKey().contains("text/plain"))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);

        if (textUrl == null) {
            throw new RuntimeException("Livro não possui versão para leitura");
        }

        textUrl = textUrl.replace("http://", "https://");

        String book = restTemplate.getForObject(textUrl, String.class);

        return cleanText(book);
    }

    // =========================
// 📄 PAGINAÇÃO (CACHEADA)
// =========================
    @Cacheable(value = "pages", key = "#id")
    public List<String> getBookPages(Long id) {

        String fullText = getFullBook(id);

        int pageSize = 1500;
        List<String> pages = new ArrayList<>();

        int start = 0;

        while (start < fullText.length()) {

            int end = Math.min(start + pageSize, fullText.length());

            if (end < fullText.length()) {
                int lastSpace = fullText.lastIndexOf(" ", end);
                if (lastSpace > start) {
                    end = lastSpace;
                }
            }

            pages.add(fullText.substring(start, end).trim());
            start = end;
        }

        return pages;
    }

    private String cleanText(String text) {

        if (text == null) return "";

        text = text.replace("\uFEFF", "");
        text = text.replace("\r\n", "\n");

        int start = text.indexOf("*** START OF");
        if (start != -1) {
            int firstLineBreak = text.indexOf("\n", start);
            text = text.substring(firstLineBreak + 1);
        }

        int end = text.indexOf("*** END OF");
        if (end != -1) {
            text = text.substring(0, end);
        }

        return text.trim();
    }

    // 🔗 LINK
    public ReadLinkDTO getReadLink(String bookId) {
        String link = "https://www.gutenberg.org/ebooks/" + bookId;
        return new ReadLinkDTO(true, link);
    }

}