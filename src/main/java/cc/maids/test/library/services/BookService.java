package cc.maids.test.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.maids.test.library.entities.Book;
import cc.maids.test.library.exceptions.BookNotFoundException;
import cc.maids.test.library.repositories.BookRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book){
        log.info("Adding a book to DB.");
        return bookRepository.save(book);
    }

    public Book getBook(Long id){
        log.info("Retrieving the book with id="+id+" from DB.");
        return bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<Book> getBooks(){
        log.info("Retrieving all books from DB.");
        List<Book> books = bookRepository.findAll();
        log.info("Retrieved " + books.size() + " books.");
        return books;
    }
    
    @Transactional
    public Book updateBook(long id, Book updatedBook){
        log.info("Updating book with id="+id);
        log.info("Looking for book with id="+id);
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
        log.info("Book is found successfully");
        book.update(updatedBook);
        log.info("Saving updated book");
        return bookRepository.save(book);
    }

    public void deleteBookWithId(long id){
        log.info("Deleting book with id="+id);
        bookRepository.deleteById(id);
    }

}
