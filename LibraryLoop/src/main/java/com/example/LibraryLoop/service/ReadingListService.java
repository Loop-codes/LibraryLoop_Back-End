package com.example.LibraryLoop.service;

import com.example.LibraryLoop.Repository.ReadingListRepository;
import com.example.LibraryLoop.Repository.SavedBookRepository;
import com.example.LibraryLoop.Repository.userRepository;
import com.example.LibraryLoop.dto.list.ListDTO;
import com.example.LibraryLoop.dto.seed.SeedDTO;
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

    public void addBookToList(String username, Long listId, SeedDTO dto) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ReadingList list = listRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        SavedBook book = new SavedBook();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setOlid(dto.getOlid());
        book.setCoverId(dto.getCoverId());
        book.setList(list);

        bookRepository.save(book);
    }
}