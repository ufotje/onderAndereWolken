package be.intec.controllers;
import be.intec.entities.NewsLetter;
import be.intec.repositories.NewsLetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("newsletter")
public class NewsLetterController {

    @Autowired
    private NewsLetterRepository newsletterRepository;

    @GetMapping(value = "/-{mail}-",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public NewsLetter getNewsLetterByMail(@PathVariable("mail")String mail){
        return newsletterRepository.findNewsLetterByMail(mail);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity subscribe(@RequestBody NewsLetter newsLetter){
        newsletterRepository.save(newsLetter);
        return new ResponseEntity(newsLetter, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteNewsletter(@PathVariable("id") int id) {
        NewsLetter newsLetter = newsletterRepository.findOne(id);
        if(newsLetter != null) {
            newsletterRepository.delete(id);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }
}