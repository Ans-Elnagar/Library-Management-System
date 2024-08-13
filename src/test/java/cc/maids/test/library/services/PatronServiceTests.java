package cc.maids.test.library.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import cc.maids.test.library.entities.Patron;
import cc.maids.test.library.exceptions.PatronNotFoundException;
import cc.maids.test.library.repositories.PatronRepository;

public class PatronServiceTests {
    
    @Mock
    private PatronRepository patronRepository;

    private Patron patron = new Patron("guy A", "a@example.com",
            "+20154785210", "Alexandria");
    long wrongId;
    @InjectMocks
    PatronService patronService;

    public PatronServiceTests() {
        MockitoAnnotations.openMocks(this);
        patron.setId(10l);
        wrongId = 1000;
    }

    @Test
    void testGetPatronByIdFound() {
        when(patronRepository.findById(patron.getId())).thenReturn(Optional.of(patron));

        Patron retrievedPatron = patronService.getPatron(patron.getId());

        assertNotNull(retrievedPatron);
        assertEquals(patron.getId(), retrievedPatron.getId());
    }

    @Test
    void testGetPatronByIdNotFound() {
        when(patronRepository.findById(wrongId)).thenReturn(Optional.empty());

        PatronNotFoundException thrownException = assertThrows(PatronNotFoundException.class, () -> {
            patronService.getPatron(wrongId);
        });
        assertEquals("Patron with id=" + wrongId + " is Not Found", thrownException.getMessage());
    }

    @Test
    void testUpdatePatronSuccess() {
        Patron editedPatron = new Patron("guy B", "a@example.com",
            "+20154785210", "Alexandria");
        editedPatron.setId(patron.getId());
        when(patronRepository.findById(patron.getId())).thenReturn(Optional.of(patron));
        when(patronRepository.save(any(Patron.class))).thenReturn(editedPatron);

        Patron updatedPatron = patronService.updatePatron(patron.getId(), editedPatron);

        assertNotNull(updatedPatron);
        assertEquals(patron.getId(), updatedPatron.getId());
        assertEquals(editedPatron.getName(), updatedPatron.getName());
    }

    @Test
    void testUpdateBookNotFound() {
        Patron editedPatron = new Patron("guy B", "a@example.com",
            "+20154785210", "Alexandria");
        editedPatron.setId(patron.getId());
        when(patronRepository.findById(wrongId)).thenReturn(Optional.empty());

        PatronNotFoundException thrownException = assertThrows(PatronNotFoundException.class, () -> {
            patronService.updatePatron(wrongId, editedPatron);
        });
        assertEquals("Patron with id=" + wrongId + " is Not Found", thrownException.getMessage());
    }
}
