package cc.maids.test.library.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorrowingRecord> borrowingRecords = new ArrayList<>();
    
    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void addBorrowingRecord(BorrowingRecord borrowingRecord) {
        borrowingRecords.add(borrowingRecord);
        borrowingRecord.setBook(this);
    }

    public void removeBorrowingRecord(BorrowingRecord borrowingRecord) {
        borrowingRecords.remove(borrowingRecord);
        borrowingRecord.setBook(null);
    }
    
    public void update(Book updatedValues){
        isbn = updatedValues.getIsbn();
        title = updatedValues.getTitle();
        author = updatedValues.getAuthor();
        publicationYear = updatedValues.getPublicationYear();
    }
}
