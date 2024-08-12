package cc.maids.test.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cc.maids.test.library.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
}