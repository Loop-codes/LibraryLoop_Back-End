package com.example.LibraryLoop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LibraryLoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryLoopApplication.class, args);
    }

}