package cc.maids.test.library.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "borrowing_records")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patronId", nullable = false)
    private Patron patron;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @Column(name = "borrowDate", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "returnDate", nullable = true)
    private LocalDate returnDate;

    public BorrowingRecord(Patron patron, Book book, LocalDate borrowDate){
        this.patron = patron;
        this.book = book;
        this.borrowDate = borrowDate;
    }
}