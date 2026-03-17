package com.example.LibraryLoop.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "saved_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String olid;
    private Integer coverId;

    @ManyToOne
    private BookList list;
}