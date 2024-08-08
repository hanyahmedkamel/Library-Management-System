package com.Almaamouny.Library.Management.System.Services;

import com.Almaamouny.Library.Management.System.Entities.Book;
import com.Almaamouny.Library.Management.System.Entities.BorrowingRecord;
import com.Almaamouny.Library.Management.System.Entities.Patron;
import com.Almaamouny.Library.Management.System.Exceptions.ResourceNotFoundException;
import com.Almaamouny.Library.Management.System.Repositories.BookRepository;
import com.Almaamouny.Library.Management.System.Repositories.BorrowingRecordRepository;
import com.Almaamouny.Library.Management.System.Repositories.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowingService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Transactional
    public ResponseEntity<String> borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + patronId));

        if (!book.isAvailable()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book is not available for borrowing");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecordRepository.save(borrowingRecord);

        return ResponseEntity.ok("Book borrowed successfully");
    }

    @Transactional
    public ResponseEntity<String> returnBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + patronId));

        Optional<BorrowingRecord> borrowingRecordOptional = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron);
        if (borrowingRecordOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No borrowing record found for this book and patron");
        }

        BorrowingRecord borrowingRecord = borrowingRecordOptional.get();
        borrowingRecord.setReturnDate(LocalDate.now());
        borrowingRecordRepository.save(borrowingRecord);

        book.setAvailable(true);
        bookRepository.save(book);

        return ResponseEntity.ok("Book returned successfully");
    }
}