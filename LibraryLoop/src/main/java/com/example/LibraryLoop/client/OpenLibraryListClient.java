package com.example.LibraryLoop.client;

import com.example.LibraryLoop.dto.ListSearchResponseDTO;
import com.example.LibraryLoop.dto.ListSeedsResponseDTO;
import com.example.LibraryLoop.dto.UserListsResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenLibraryListClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://openlibrary.org";

    public UserListsResponseDTO getUserLists(String user) {
        String url = BASE_URL + "/people/" + user + "/lists.json";
        return restTemplate.getForObject(url, UserListsResponseDTO.class);
    }

    public ListSearchResponseDTO searchLists(String query) {
        String url = BASE_URL + "/search/lists.json?q=" + query;
        return restTemplate.getForObject(url, ListSearchResponseDTO.class);
    }

    public ListSeedsResponseDTO getSeeds(String user, String listId) {
        String url = BASE_URL + "/people/" + user + "/lists/" + listId + "/seeds.json";
        return restTemplate.getForObject(url, ListSeedsResponseDTO.class);
    }
}