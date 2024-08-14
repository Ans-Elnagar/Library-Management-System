package cc.maids.test.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cc.maids.test.library.entities.Book;
import cc.maids.test.library.services.BookService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@Slf4j
@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        log.info("Received a GET request to fetch all books in DB");
        return bookService.getBooks();
    }
    
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getBookWithId(@PathVariable Long id) {
        log.info("Received a GET request to fetch the book with id=" + id + "from DB");
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping("/api/books")
    public Long addBook(@RequestBody Book book) {
        log.info("Received a POST request to add "+book+" to DB");
        book = bookService.addBook(book);
        log.info("The book was added successfully with id="+book.getId());
        return book.getId();
    }

    @PutMapping("/api/books/{id}")
    public Book updateBookWithId(@PathVariable Long id, @RequestBody Book updatedBook) {
        log.info("Received a PUT request to update the book with id="
            +id+"to be "+updatedBook);
        return bookService.updateBook(id, updatedBook);
    }
    
    @DeleteMapping("/api/books/{id}")
    public void deleteBookWithId(@PathVariable Long id){
        log.info("Received a DELETE request to delete the book with id="+id);
        bookService.deleteBookWithId(id);
    }
}
