package com.Almaamouny.Library.Management.System.Repositories;

import com.Almaamouny.Library.Management.System.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
