package cc.maids.test.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cc.maids.test.library.entities.Book;
import cc.maids.test.library.services.BookService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
    
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getBookWithId(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping("/api/books")
    public Long addBook(@RequestBody Book book) {
        book = bookService.addBook(book);
        return book.getId();
    }

    @PutMapping("/api/books/{id}")
    public Book updateBookWithId(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }
    
    @DeleteMapping("/api/books/{id}")
    public void deleteBookWithId(@PathVariable Long id){
        bookService.deleteBookWithId(id);
    }
}
