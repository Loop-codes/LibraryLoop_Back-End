package com.example.LibraryLoop.Repository;


import com.example.LibraryLoop.entity.SavedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedBookRepository extends JpaRepository<SavedBook, Long> {

    List<SavedBook> findByList_Id(Long listId);
}