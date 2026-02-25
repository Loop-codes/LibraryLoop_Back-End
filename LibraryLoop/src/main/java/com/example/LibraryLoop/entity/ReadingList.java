package com.example.LibraryLoop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reading_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}