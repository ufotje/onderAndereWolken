package be.intec.controllers;

import be.intec.entities.Guestbook;
import be.intec.repositories.GuestbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("guestbook")
public class GuestbookController {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Guestbook> getAllGuestbookEntries() {
        return guestbookRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Guestbook getGuestbookById(@PathVariable("id") int id) {
        return guestbookRepository.findOne(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addGuestbook(@RequestBody Guestbook guestbook) {
        guestbook.setPostDate(LocalDateTime.now());
        guestbookRepository.save(guestbook);
        return new ResponseEntity(guestbook, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteGuestbook(@PathVariable("id") int id) {
        Guestbook guestbook = guestbookRepository.findOne(id);
        if (guestbook != null) {
            guestbookRepository.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
