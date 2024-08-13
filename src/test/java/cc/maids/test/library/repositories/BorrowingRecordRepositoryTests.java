package cc.maids.test.library.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;

import cc.maids.test.library.entities.Book;
import cc.maids.test.library.entities.BorrowingRecord;
import cc.maids.test.library.entities.Patron;



@DataJpaTest
public class BorrowingRecordRepositoryTests {

    @Autowired
    PatronRepository patronRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    @Test
    void testFindByBoodIdAndPatronId() {
        Patron patron = new Patron("guy A", "a@example.com",
            "+20154785210", "Alexandria");
        patron = patronRepository.save(patron);

        Book book = new Book("Dreams", "some guy",
             "fdakfkadsfa", 2010);
        book = bookRepository.save(book);
        
        BorrowingRecord borrowingRecord = 
            new BorrowingRecord(patron, book, LocalDate.now());
        borrowingRecord = borrowingRecordRepository.save(borrowingRecord);

        BorrowingRecord retrievedBorrowingRecord = borrowingRecordRepository
            .findByBookIdAndPatronId(book.getId(), patron.getId()).orElse(null);

        assertNotNull(retrievedBorrowingRecord);
        assertEquals(borrowingRecord.getId(), retrievedBorrowingRecord.getId());
        assertEquals(borrowingRecord.getBook().getId(),
            retrievedBorrowingRecord.getBook().getId());
        assertEquals(borrowingRecord.getPatron().getId(), 
            retrievedBorrowingRecord.getPatron().getId());
        assertEquals(borrowingRecord.getBorrowDate(),
            retrievedBorrowingRecord.getBorrowDate());
    }
    
}
