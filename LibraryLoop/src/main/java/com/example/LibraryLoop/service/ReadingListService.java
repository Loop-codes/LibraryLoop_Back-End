package com.example.LibraryLoop.service;

import com.example.LibraryLoop.Repository.ReadingListRepository;
import com.example.LibraryLoop.Repository.SavedBookRepository;
import com.example.LibraryLoop.Repository.userRepository;
import com.example.LibraryLoop.dto.*;
import com.example.LibraryLoop.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingListService {

    private final userRepository userRepository;
    private final ReadingListRepository listRepository;
    private final SavedBookRepository bookRepository;

    // cria usuário se não existir
    private User getOrCreateUser(String username) {
        return userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.save(User.builder()
                        .username(username)
                        .build()));
    }

    public List<ListDTO> getListsFromUser(String username) {
        return listRepository.findByUser_Username(username)
                .stream()
                .map(l -> new ListDTO(l.getId(), l.getName()))
                .toList();
    }

    public List<SeedDTO> getBooksFromList(String username, String listId) {

        return bookRepository.findByList_Id(Long.parseLong(listId))
                .stream()
                .map(b -> new SeedDTO(
                        b.getTitle(),
                        b.getAuthor(),
                        b.getOlid(),
                        b.getCoverId()))
                .toList();
    }

    public void createList(String username, String name) {

        User user = getOrCreateUser(username);

        listRepository.save(
                ReadingList.builder()
                        .name(name)
                        .user(user)
                        .build()
        );
    }

    public void addBookToList(String username, String listId, SeedDTO dto) {

        ReadingList list = listRepository.findById(Long.parseLong(listId))
                .orElseThrow();

        bookRepository.save(
                SavedBook.builder()
                        .title(dto.getTitle())
                        .author(dto.getAuthor())
                        .olid(dto.getOlid())
                        .coverId(dto.getCoverId())
                        .list(list)
                        .build()
        );
    }
}