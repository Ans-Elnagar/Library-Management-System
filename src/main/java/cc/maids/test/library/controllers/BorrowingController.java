package cc.maids.test.library.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import cc.maids.test.library.services.BorrowingService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@Slf4j
@RestController
public class BorrowingController {

    @Autowired
    BorrowingService borrowingService;

    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    public boolean borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        log.info("Received a POST request to make patron with id="+patronId
            +" borrow the book with id="+bookId );
        borrowingService
            .borrowBook(bookId, patronId, LocalDate.now());
        return true;
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public Boolean returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        log.info("Received a POST request to make patron with id="+patronId
            +" return the book with id="+bookId );
        borrowingService
            .returnBook(bookId, patronId, LocalDate.now());
        return true;
    }
}
