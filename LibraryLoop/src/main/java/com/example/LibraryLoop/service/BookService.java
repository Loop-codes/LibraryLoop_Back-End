package com.example.LibraryLoop.service;

import com.example.LibraryLoop.client.GutendexClient;
import com.example.LibraryLoop.dto.gutendex.GutendexAuthor;
import com.example.LibraryLoop.dto.gutendex.GutendexBookResponse;
import com.example.LibraryLoop.dto.book.BookSearchDTO;
import com.example.LibraryLoop.dto.gutendex.GutendexResponse;
import com.example.LibraryLoop.dto.read.ReadLinkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final GutendexClient gutendexClient;
    private final RestTemplate restTemplate; // ✅ injetado corretamente


    public List<BookSearchDTO> searchBooks(String title, int limit) {

        GutendexResponse response =
                gutendexClient.searchBooks(title);

        return response.getResults()
                .stream()
                .limit(limit)
                .map(book -> new BookSearchDTO(
                        String.valueOf(book.getId()),
                        book.getTitle(),
                        book.getAuthors()
                                .stream()
                                .map(GutendexAuthor::getName)
                                .toList(),
                        null,
                        null,
                        null,
                        null,
                        true,
                        null
                ))
                .toList();
    }

    public List<String> getBookPages(Long id) {

        String fullText = readBook(id);

        int pageSize = 1500;
        List<String> pages = new ArrayList<>();

        int start = 0;

        while (start < fullText.length()) {

            int end = Math.min(start + pageSize, fullText.length());

            // evita cortar palavra no meio
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

        // remove BOM
        text = text.replace("\uFEFF", "");

        // normaliza quebra de linha
        text = text.replace("\r\n", "\n");

        // remove cabeçalho
        int start = text.indexOf("*** START OF");
        if (start != -1) {
            int firstLineBreak = text.indexOf("\n", start);
            text = text.substring(firstLineBreak + 1);
        }

        // remove rodapé
        int end = text.indexOf("*** END OF");
        if (end != -1) {
            text = text.substring(0, end);
        }

        // remove espaços excessivos
        text = text.replaceAll("\n{3,}", "\n\n");

        return text.trim();
    }

    public String readBook(Long id) {

        String url = "https://gutendex.com/books/" + id;

        GutendexBookResponse response =
                restTemplate.getForObject(url, GutendexBookResponse.class);

        if (response == null || response.getFormats() == null) {
            return "Livro não encontrado";
        }

        String textUrl = response.getFormats()
                .entrySet()
                .stream()
                .filter(f -> f.getKey().contains("text/plain; charset=utf-8")) // ✅ CORRETO
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);

        if (textUrl == null) {
            throw new RuntimeException("Livro não encontrado");
        }

        // força https
        textUrl = textUrl.replace("http://", "https://");

        String book = restTemplate.getForObject(textUrl, String.class);

        if (book == null) {
            return "Erro ao carregar o livro";
        }

        // remove BOM estranho
        book = book.replace("\uFEFF", "");

        // 🔥 LIMPEZA DO GUTENBERG
        book = cleanText(book);

        return book;
    }

    public ReadLinkDTO getReadLink(String bookId) {

        String link = "https://www.gutenberg.org/ebooks/" + bookId;

        return new ReadLinkDTO(true, link);
    }
}