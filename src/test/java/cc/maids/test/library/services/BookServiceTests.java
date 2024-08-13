package cc.maids.test.library.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import cc.maids.test.library.entities.Book;
import cc.maids.test.library.exceptions.BookNotFoundException;
import cc.maids.test.library.repositories.BookRepository;

public class BookServiceTests {
    
    @Mock
    private BookRepository bookRepository;

    private Book book = new Book("Dreams", "some guy",
             "fdakfkadsfa", 2010);
    long wrongId;
    @InjectMocks
    BookService bookService;

    public BookServiceTests() {
        MockitoAnnotations.openMocks(this);
        book.setId(10l);
        wrongId = book.getId()+1000;
    }

    @Test
    void testGetBookByIdFound() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        Book retrievedBook = bookService.getBook(book.getId());

        assertNotNull(retrievedBook);
        assertEquals(book.getId(), retrievedBook.getId());
    }

    @Test
    void testGetBookByIdNotFound() {
        when(bookRepository.findById(wrongId)).thenReturn(Optional.empty());

        BookNotFoundException thrownException = assertThrows(BookNotFoundException.class, () -> {
            bookService.getBook(wrongId);
        });
        assertEquals("Book with id=" + wrongId + " is Not Found", thrownException.getMessage());
    }

    @Test
    void testUpdateBookSuccess() {
        Book editedBook = new Book("Hopeful Dreams", "some guy",
            "fdakfkadsfa", 2010);
        editedBook.setId(book.getId());
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(editedBook);

        Book updatedBook = bookService.updateBook(book.getId(), editedBook);

        assertNotNull(updatedBook);
        assertEquals(book.getId(), updatedBook.getId());
        assertEquals(editedBook.getTitle(), updatedBook.getTitle());
    }

    @Test
    void testUpdateBookNotFound() {
        Book editedBook = new Book("Hopeful Dreams", "some guy",
            "fdakfkadsfa", 2010);
        editedBook.setId(book.getId());
        when(bookRepository.findById(wrongId)).thenReturn(Optional.empty());

        BookNotFoundException thrownException = assertThrows(BookNotFoundException.class, () -> {
            bookService.updateBook(wrongId, editedBook);
        });
        assertEquals("Book with id=" + wrongId + " is Not Found", thrownException.getMessage());
    }
}
