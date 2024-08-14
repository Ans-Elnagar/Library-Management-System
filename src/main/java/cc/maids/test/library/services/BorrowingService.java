package cc.maids.test.library.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.maids.test.library.entities.Book;
import cc.maids.test.library.entities.BorrowingRecord;
import cc.maids.test.library.entities.Patron;
import cc.maids.test.library.exceptions.BorrowingRecordNotFoundException;
import cc.maids.test.library.repositories.BorrowingRecordRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BorrowingService {
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    BookService bookService;
    @Autowired
    PatronService patronService;

    @Transactional
    public BorrowingRecord borrowBook(long bookId, long patronId, LocalDate borrowDate){
        log.info("Starting transaction of borrowing");
        log.info("Looking for the book with id="+bookId);
        Book book = bookService.getBook(bookId);
        log.info("The book is found");
        log.info("Looking for the patron with id="+patronId);
        Patron patron = patronService.getPatron(patronId);
        log.info("The patron is found");
        BorrowingRecord borrowingRecord = new BorrowingRecord(patron, book, borrowDate);
        log.info("Borrowing the book and ending the transaction.");
        return borrowingRecordRepository.save(borrowingRecord);
    }

    @Transactional
    public BorrowingRecord returnBook(long bookId, long patronId, LocalDate returnDate){
        log.info("Looking for the borrowing record of book id="
            +bookId+ " and patron id="+patronId);
        BorrowingRecord borrowingRecord = borrowingRecordRepository
            .findByBookIdAndPatronId(bookId, patronId)
            .orElseThrow(() -> new BorrowingRecordNotFoundException(bookId, patronId));
        log.info("Record is found");
        borrowingRecord.setReturnDate(returnDate);
        log.info("Setting return date and storing the record");
        return borrowingRecordRepository.save(borrowingRecord);
    }
}
