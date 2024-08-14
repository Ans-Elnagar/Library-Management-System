package cc.maids.test.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cc.maids.test.library.entities.Patron;
import cc.maids.test.library.services.PatronService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@Slf4j
@RestController
public class PatronController {
    @Autowired
    PatronService patronService;

    @GetMapping("/api/patrons")
    public List<Patron> getPatrons() {
        log.info("Received a GET request to fetch all books in DB");
        return patronService.getPatrons();
    }
    
    @GetMapping("/api/patrons/{id}")
    public ResponseEntity<Patron> getPatronWithId(@PathVariable Long id) {
        log.info("Received a GET request to fetch the patron with id=" + id + "from DB");
        return ResponseEntity.ok(patronService.getPatron(id));
    }

    @PostMapping("/api/patrons")
    public Long addPatron(@RequestBody Patron patron) {
        log.info("Received a POST request to add "+patron+" to DB");
        patron = patronService.addPatron(patron);
        log.info("The patron was added successfully with id="+patron.getId());
        return patron.getId();
    }

    @PutMapping("/api/patrons/{id}")
    public Patron updatePatronWithId(@PathVariable Long id, @RequestBody Patron updatedPatron) {
        log.info("Received a PUT request to update the patron with id="
            +id+"to be "+updatedPatron);
        return patronService.updatePatron(id, updatedPatron);
    }
    
    @DeleteMapping("/api/patrons/{id}")
    public void deletePatronWithId(@PathVariable Long id){
        log.info("Received a DELETE request to delete the patron with id="+id);
        patronService.deletePatronWithId(id);
    }
}
