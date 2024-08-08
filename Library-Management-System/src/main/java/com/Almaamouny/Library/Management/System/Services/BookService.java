package com.Almaamouny.Library.Management.System.Services;

import com.Almaamouny.Library.Management.System.Entities.Book;
import com.Almaamouny.Library.Management.System.Exceptions.ResourceNotFoundException;
import com.Almaamouny.Library.Management.System.Repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Cacheable(value = "books", key = "'all'")

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Cacheable(value = "books", key = "#id")

    public ResponseEntity<Book> getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        return ResponseEntity.ok(book);
    }

    @Transactional

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @CacheEvict(value = "books", allEntries = true)
    public ResponseEntity<Book> updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setIsbn(bookDetails.getIsbn());
        book.setAvailable(bookDetails.isAvailable());
        Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @Transactional
    @CacheEvict(value = "books", key = "#id")

    public ResponseEntity<Void> deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }
}