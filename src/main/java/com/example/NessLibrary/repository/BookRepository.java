package com.example.NessLibrary.repository;

import com.example.NessLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByAuthorAscTitleAsc();
    List<Book> findByTitleContainingIgnoreCase(String title);
}

