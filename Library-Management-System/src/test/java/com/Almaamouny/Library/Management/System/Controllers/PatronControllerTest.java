package com.Almaamouny.Library.Management.System.Controllers;

import com.Almaamouny.Library.Management.System.Entities.Patron;
import com.Almaamouny.Library.Management.System.Services.PatronService;
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

class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    private Patron patron;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patron = new Patron();
        patron.setId(1L);
        patron.setName("John Doe");
    }

    @Test
    void getAllPatrons_ShouldReturnListOfPatrons() {
        List<Patron> patrons = Arrays.asList(patron, new Patron());
        when(patronService.getAllPatrons()).thenReturn(patrons);

        List<Patron> result = patronController.getAllPatrons();

        assertEquals(2, result.size());
        verify(patronService, times(1)).getAllPatrons();
    }

    @Test
    void getPatronById_ShouldReturnPatron() {
        when(patronService.getPatronById(1L)).thenReturn(ResponseEntity.of(Optional.of(patron)));

        ResponseEntity<Patron> response = patronController.getPatronById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
        verify(patronService, times(1)).getPatronById(1L);
    }

    @Test
    void addPatron_ShouldReturnSavedPatron() {
        when(patronService.addPatron(any(Patron.class))).thenReturn(patron);

        Patron savedPatron = patronController.addPatron(patron);

        assertEquals(patron, savedPatron);
        verify(patronService, times(1)).addPatron(any(Patron.class));
    }

    @Test
    void updatePatron_ShouldReturnUpdatedPatron() {
        when(patronService.updatePatron(eq(1L), any(Patron.class))).thenReturn(ResponseEntity.ok(patron));

        ResponseEntity<Patron> response = patronController.updatePatron(1L, patron);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
        verify(patronService, times(1)).updatePatron(eq(1L), any(Patron.class));
    }

    @Test
    void deletePatron_ShouldReturnNoContent() {
        when(patronService.deletePatron(1L)).thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<Void> response = patronController.deletePatron(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(patronService, times(1)).deletePatron(1L);
    }
}
