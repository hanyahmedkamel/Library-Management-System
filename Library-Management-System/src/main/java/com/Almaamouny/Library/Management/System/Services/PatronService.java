package com.Almaamouny.Library.Management.System.Services;

import com.Almaamouny.Library.Management.System.Entities.Patron;
import com.Almaamouny.Library.Management.System.Exceptions.ResourceNotFoundException;
import com.Almaamouny.Library.Management.System.Repositories.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Cacheable(value = "patrons", key = "'all'")

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Cacheable(value = "patrons", key = "#id")

    public ResponseEntity<Patron> getPatronById(Long id) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + id));
        return ResponseEntity.ok(patron);
    }

    @Transactional
    public Patron addPatron(Patron patron) {
        patron.setPassword(bCryptPasswordEncoder.encode(patron.getPassword()));
        return patronRepository.save(patron);
    }

    @Transactional
    @CacheEvict(value = "patrons", allEntries = true)

    public ResponseEntity<Patron> updatePatron(Long id, Patron patronDetails) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + id));
        patron.setName(patronDetails.getName());
        patron.setEmail(patronDetails.getEmail());
        patron.setPhoneNumber(patronDetails.getPhoneNumber());
        Patron updatedPatron = patronRepository.save(patron);
        return ResponseEntity.ok(updatedPatron);
    }

    @Transactional
    @CacheEvict(value = "patrons", key = "#id")
    public ResponseEntity<Void> deletePatron(Long id) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found with id " + id));
        patronRepository.delete(patron);
        return ResponseEntity.noContent().build();
    }
}