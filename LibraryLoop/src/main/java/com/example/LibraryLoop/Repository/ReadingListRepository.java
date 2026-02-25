package com.example.LibraryLoop.Repository;

import com.example.LibraryLoop.entity.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<ReadingList, Long> {

    List<ReadingList> findByUser_Username(String username);
}