package cc.maids.test.library.exceptions;

public class BorrowingRecordNotFoundException extends RuntimeException {
    public BorrowingRecordNotFoundException(long bookId, long patronId) {
        super("There is no record of patronId:"
                +patronId+" borrowing bookId:" + bookId);
    }
}