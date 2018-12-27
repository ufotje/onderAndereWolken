package be.intec.controllers;

import be.intec.entities.Comment;
import be.intec.entities.Story;
import be.intec.repositories.CommentRepository;
import be.intec.repositories.StoryRepository;
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

/**
 * Created by PearlS on 6/11/2017.
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    StoryRepository storyRepository;
    @Autowired
    CommentRepository commentRepository;


    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Comment getCommentById(@PathVariable("id") int id) {
        return commentRepository.findOne(id);
    }

    @GetMapping(value = "story/{storyid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Comment> getAllCommentsByStory(@PathVariable("storyid") String storyid) {
        Story story = storyRepository.findStoryById(Integer.parseInt(storyid));
        return commentRepository.findCommentByStory(story);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addComment(@RequestBody Comment comment) {
        comment.setPostDate(LocalDateTime.now());
        commentRepository.save(comment);
        return new ResponseEntity(comment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteComment(@PathVariable("id") int id) {
        Comment comment = commentRepository.findOne(id);
        if (comment != null) {
            commentRepository.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
