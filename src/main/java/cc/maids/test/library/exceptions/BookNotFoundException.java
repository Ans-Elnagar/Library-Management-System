package cc.maids.test.library.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long bookId) {
        super("Book with id=" + bookId + " is Not Found");
    }
}