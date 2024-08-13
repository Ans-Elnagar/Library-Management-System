package cc.maids.test.library.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cc.maids.test.library.entities.Book;

@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testFindById() {
        Book book = new Book("Dreams", "some guy",
             "fdakfkadsfa", 2010);
        long id = bookRepository.save(book).getId();

        Book retrievedBook = bookRepository.findById(id).orElse(null);

        assertNotNull(retrievedBook);
        assertEquals(id, retrievedBook.getId());
        assertEquals(book.getTitle(), retrievedBook.getTitle());
        assertEquals(book.getIsbn(), retrievedBook.getIsbn());
        assertEquals(book.getAuthor(), retrievedBook.getAuthor());
        assertEquals(book.getPublicationYear(), retrievedBook.getPublicationYear());
    }
    
}
