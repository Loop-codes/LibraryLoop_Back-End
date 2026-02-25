package com.example.LibraryLoop.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenLibraryClient {

    private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://openlibrary.org";

    public OpenLibraryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchBooks(String title) {

        String url = BASE_URL + "/search.json?title=" + title;

        return restTemplate.getForObject(url, String.class);
    }

    public String getEditions(String workId) {

        String url = BASE_URL + "/works/" + workId + "/editions.json";

        return restTemplate.getForObject(url, String.class);
    }
}