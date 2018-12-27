package client.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PearlS on 31/10/2017.
 */
public class Tag implements Serializable {

    private int id;
    private String word;
    private List<Story> stories = new ArrayList<>();

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

    @Override
    public String toString() {
        return  word;
    }
}
