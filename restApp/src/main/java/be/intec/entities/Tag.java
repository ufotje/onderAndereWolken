package be.intec.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PearlS on 11/10/2017.
 */
@Entity
@Table(name = "tag")
public class Tag implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "word", unique = true)
    private String word;
    @ManyToMany (mappedBy = "tags", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnoreProperties("tags")
    private List<Story> stories = new ArrayList<>();

    public Tag() {
        super();
    }

    public Tag(String word, List<Story> stories) {
        this();
        this.word = word;
        this.stories = stories;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    /*@Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", stories=" + stories +
                '}';
    }*/
}
