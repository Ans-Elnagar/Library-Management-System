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
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;
    
    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorrowingRecord> borrowingRecords = new ArrayList<>();

    public Patron(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void addBorrowingRecord(BorrowingRecord borrowingRecord) {
        borrowingRecords.add(borrowingRecord);
        borrowingRecord.setPatron(this);
    }

    public void removeBorrowingRecord(BorrowingRecord borrowingRecord) {
        borrowingRecords.remove(borrowingRecord);
        borrowingRecord.setPatron(null);
    }

    public void update(Patron patron){
        name = patron.getName();
        email = patron.getEmail();
        phone = patron.getPhone();
        address = patron.getAddress();
    }
}