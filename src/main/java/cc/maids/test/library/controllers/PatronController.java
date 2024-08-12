package cc.maids.test.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cc.maids.test.library.entities.Patron;
import cc.maids.test.library.services.PatronService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class PatronController {
    @Autowired
    PatronService patronService;

    @GetMapping("/api/patrons")
    public List<Patron> getPatrons() {
        return patronService.getPatrons();
    }
    
    @GetMapping("/api/patrons/{id}")
    public ResponseEntity<Patron> getPatronWithId(@PathVariable Long id) {
        return ResponseEntity.ok(patronService.getPatron(id));
    }

    @PostMapping("/api/patrons")
    public Long addPatron(@RequestBody Patron patron) {
        patron = patronService.addPatron(patron);
        return patron.getId();
    }

    @PutMapping("/api/patrons/{id}")
    public Boolean updatePatronWithId(@PathVariable Long id, @RequestBody Patron updatedPatron) {
        return patronService.updatePatron(id, updatedPatron);
    }
    
    @DeleteMapping("/api/patrons/{id}")
    public void deletePatronWithId(@PathVariable Long id){
        patronService.deletePatronWithId(id);
    }
}
