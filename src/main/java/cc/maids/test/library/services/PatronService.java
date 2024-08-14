package cc.maids.test.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.maids.test.library.entities.Patron;
import cc.maids.test.library.exceptions.PatronNotFoundException;
import cc.maids.test.library.repositories.PatronRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    public Patron addPatron(Patron patron){
        log.info("Adding a patron to DB.");
        return patronRepository.save(patron);
    }

    public Patron getPatron(Long id){
        log.info("Retrieving the patron with id="+id+" from DB.");
        return patronRepository.findById(id)
            .orElseThrow(() -> new PatronNotFoundException(id));
    }

    public List<Patron> getPatrons(){
        log.info("Retrieving all patrons from DB.");
        List<Patron> patrons = patronRepository.findAll();
        log.info("Retrieved " + patrons.size() + " patrons.");
        return patrons;
    }

    @Transactional
    public Patron updatePatron(long id, Patron updatedPatron){
        log.info("Updating patron with id="+id);
        log.info("Looking for patron with id="+id);
        Patron patron = patronRepository.findById(id)
            .orElseThrow(() -> new PatronNotFoundException(id));
        log.info("Patron is found successfully");
        patron.update(updatedPatron);
        log.info("Saving updated patron");
        return patronRepository.save(patron);
    }

    public void deletePatronWithId(long id){
        log.info("Deleting patron with id="+id);
        patronRepository.deleteById(id);
    }

}
