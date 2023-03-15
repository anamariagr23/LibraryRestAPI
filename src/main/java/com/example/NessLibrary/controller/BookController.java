package com.example.NessLibrary.controller;

import com.example.NessLibrary.model.Book;
import com.example.NessLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;


    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAllByOrderByAuthorAscTitleAsc();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Book> searchBooksByTitle(@RequestParam("title") String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (!optionalBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Book book = optionalBook.get();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());

        Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }
}

