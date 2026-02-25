package com.example.LibraryLoop.controller;

import com.example.LibraryLoop.dto.list.ListDTO;
import com.example.LibraryLoop.dto.seed.SeedDTO;
import com.example.LibraryLoop.service.ReadingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ReadingListController {

    private final ReadingListService service;

    @GetMapping("/{username}/lists")
    public List<ListDTO> getUserLists(@PathVariable String username) {
        return service.getListsFromUser(username);
    }

    @PostMapping("/{username}/lists")
    public void createList(
            @PathVariable String username,
            @RequestParam String name) {

        service.createList(username, name);
    }

    @GetMapping("/{username}/lists/{listId}/books")
    public List<SeedDTO> getBooksFromList(
            @PathVariable String username,
            @PathVariable String listId) {

        return service.getBooksFromList(username, listId);
    }

    @PostMapping("/{username}/lists/{listId}/books")
    public void addBook(
            @PathVariable String username,
            @PathVariable String listId,
            @RequestBody SeedDTO dto) {

        service.addBookToList(username, Long.valueOf(listId), dto);
    }
}