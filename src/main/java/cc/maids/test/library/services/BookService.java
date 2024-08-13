package cc.maids.test.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.maids.test.library.entities.Book;
import cc.maids.test.library.exceptions.BookNotFoundException;
import cc.maids.test.library.repositories.BookRepository;
import jakarta.transaction.Transactional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book getBook(Long id){
        return bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<Book> getBooks(){
        List<Book> books = bookRepository.findAll();
        return books;
    }
    
    @Transactional
    public Book updateBook(long id, Book updatedBook){
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
        book.update(updatedBook);
        return bookRepository.save(book);
    }

    public void deleteBookWithId(long id){
        bookRepository.deleteById(id);
    }

}
