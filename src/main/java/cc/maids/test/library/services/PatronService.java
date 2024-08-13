package cc.maids.test.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.maids.test.library.entities.Patron;
import cc.maids.test.library.exceptions.PatronNotFoundException;
import cc.maids.test.library.repositories.PatronRepository;
import jakarta.transaction.Transactional;

@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    public Patron addPatron(Patron patron){
        return patronRepository.save(patron);
    }

    public Patron getPatron(Long id){
        return patronRepository.findById(id)
            .orElseThrow(() -> new PatronNotFoundException(id));
    }

    public List<Patron> getPatrons(){
        List<Patron> patrons = patronRepository.findAll();
        return patrons;
    }

    @Transactional
    public Patron updatePatron(long id, Patron updatedPatron){
        Patron patron = patronRepository.findById(id)
            .orElseThrow(() -> new PatronNotFoundException(id));
        patron.update(updatedPatron);
        return patronRepository.save(patron);
    }

    public void deletePatronWithId(long id){
        patronRepository.deleteById(id);
    }

}
