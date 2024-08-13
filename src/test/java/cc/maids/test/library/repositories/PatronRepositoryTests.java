package cc.maids.test.library.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cc.maids.test.library.entities.Patron;


@DataJpaTest
public class PatronRepositoryTests {

    @Autowired
    PatronRepository patronRepository;

    @Test
    void testFindById() {
        Patron patron = new Patron("guy A", "a@example.com",
            "+20154785210", "Alexandria");

        long id = patronRepository.save(patron).getId();
        
        Patron retrievedPatron = patronRepository.findById(id).orElse(null);

        assertNotNull(retrievedPatron);
        assertEquals(id, retrievedPatron.getId());
        assertEquals(patron.getName(), retrievedPatron.getName());
        assertEquals(patron.getEmail(), retrievedPatron.getEmail());
        assertEquals(patron.getPhone(), retrievedPatron.getPhone());
        assertEquals(patron.getAddress(), retrievedPatron.getAddress());
    }
    
}
