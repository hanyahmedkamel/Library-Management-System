package com.Almaamouny.Library.Management.System.Controllers;


import com.Almaamouny.Library.Management.System.Services.BorrowingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BorrowingControllerTest {

    @Mock
    private BorrowingService borrowingService;

    @InjectMocks
    private BorrowingController borrowingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void borrowBook_ShouldReturnSuccessMessage() {
        Long bookId = 1L;
        Long patronId = 1L;
        String successMessage = "Book borrowed successfully!";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(successMessage, HttpStatus.OK);

        when(borrowingService.borrowBook(bookId, patronId)).thenReturn(expectedResponse);

        ResponseEntity<String> response = borrowingController.borrowBook(bookId, patronId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successMessage, response.getBody());
        verify(borrowingService, times(1)).borrowBook(bookId, patronId);
    }

    @Test
    void returnBook_ShouldReturnSuccessMessage() {
        Long bookId = 1L;
        Long patronId = 1L;
        String successMessage = "Book returned successfully!";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(successMessage, HttpStatus.OK);

        when(borrowingService.returnBook(bookId, patronId)).thenReturn(expectedResponse);

        ResponseEntity<String> response = borrowingController.returnBook(bookId, patronId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(successMessage, response.getBody());
        verify(borrowingService, times(1)).returnBook(bookId, patronId);
    }
}
