package cc.maids.test.library.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import cc.maids.test.library.entities.Book;
import cc.maids.test.library.entities.BorrowingRecord;
import cc.maids.test.library.entities.Patron;
import cc.maids.test.library.exceptions.BorrowingRecordNotFoundException;
import cc.maids.test.library.repositories.BorrowingRecordRepository;
import jakarta.transaction.Transactional;

public class BorrowingService {
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    BookService bookService;
    @Autowired
    PatronService patronService;

    @Transactional
    public BorrowingRecord borrowBook(long bookId, long patronId, LocalDate borrowDate){
        Book book = bookService.getBook(bookId);
        Patron patron = patronService.getPatron(patronId);
        BorrowingRecord borrowingRecord = new BorrowingRecord(patron, book, borrowDate);
        return borrowingRecordRepository.save(borrowingRecord);
    }

    @Transactional
    public BorrowingRecord returnBook(long bookId, long patronId, LocalDate returnDate){
        BorrowingRecord borrowingRecord = borrowingRecordRepository
            .findByBookIdAndPatronId(bookId, patronId)
            .orElseThrow(() -> new BorrowingRecordNotFoundException(bookId, patronId));

        borrowingRecord.setReturnDate(returnDate);
        return borrowingRecordRepository.save(borrowingRecord);
    }
}
