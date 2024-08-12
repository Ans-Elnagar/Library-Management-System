package cc.maids.test.library.exceptions;

public class PatronNotFoundException extends RuntimeException {
    public PatronNotFoundException(Long patronId) {
        super("Patron with id=" + patronId + " is Not Found");
    }
}