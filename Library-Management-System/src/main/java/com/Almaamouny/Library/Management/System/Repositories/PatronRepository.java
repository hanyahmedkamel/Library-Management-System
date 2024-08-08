package com.Almaamouny.Library.Management.System.Repositories;

import com.Almaamouny.Library.Management.System.Entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
    Patron findByUsername(String username);

}
