package be.intec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Size(min = 1, max = 100, message = "Aantal karakters moet tussen 1 en 240 zijn.")
    private String body;
    @Column(name = "postDate")
    private LocalDateTime postDate;
    @ManyToOne
    @JoinColumn(name = "story")
    @JsonBackReference(value = "storyComments")
    private Story story;

    public Comment() {
    }

    public Comment(String body, LocalDateTime postDate, Story story) {
        this.body = body;
        this.postDate = postDate;
        this.story = story;
    }

    public int getId() {
        return id;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
