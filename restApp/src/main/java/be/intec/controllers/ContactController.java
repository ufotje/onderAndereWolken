package be.intec.controllers;

import be.intec.entities.Contact;
import be.intec.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by PearlS on 13/11/2017.
 */
@RestController
@RequestMapping ("contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Contact finContactById(@PathVariable("id") int id){
        return contactRepository.findOne(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addComment(@RequestBody Contact contact) {
        contact.setPostDate(LocalDateTime.now());
        contactRepository.save(contact);
        return new ResponseEntity(contact, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteComment(@PathVariable("id") int id) {
        Contact contact = contactRepository.findOne(id);
        if(contact != null) {
            contactRepository.delete(id);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }


}
