package com.Almaamouny.Library.Management.System.Controllers;


import com.Almaamouny.Library.Management.System.Entities.Book;
import com.Almaamouny.Library.Management.System.Services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() {
        List<Book> books = Arrays.asList(book, new Book());
        when(bookService.getAllBooks()).thenReturn(books);

        List<Book> result = bookController.getAllBooks();

        assertEquals(2, result.size());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void getBookById_ShouldReturnBook() {
        when(bookService.getBookById(1L)).thenReturn(ResponseEntity.of(Optional.of(book)));

        ResponseEntity<Book> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void addBook_ShouldReturnSavedBook() {
        when(bookService.addBook(any(Book.class))).thenReturn(book);

        Book savedBook = bookController.addBook(book);

        assertEquals(book, savedBook);
        verify(bookService, times(1)).addBook(any(Book.class));
    }

    @Test
    void updateBook_ShouldReturnUpdatedBook() {
        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(ResponseEntity.ok(book));

        ResponseEntity<Book> response = bookController.updateBook(1L, book);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).updateBook(eq(1L), any(Book.class));
    }

    @Test
    void deleteBook_ShouldReturnNoContent() {
        when(bookService.deleteBook(1L)).thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<Void> response = bookController.deleteBook(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService, times(1)).deleteBook(1L);
    }
}
