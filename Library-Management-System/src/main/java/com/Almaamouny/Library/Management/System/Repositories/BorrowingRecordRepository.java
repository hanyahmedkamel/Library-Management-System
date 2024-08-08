package com.Almaamouny.Library.Management.System.Repositories;

import com.Almaamouny.Library.Management.System.Entities.Book;
import com.Almaamouny.Library.Management.System.Entities.BorrowingRecord;
import com.Almaamouny.Library.Management.System.Entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);

}
