package client.entities;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;


public class Comment implements Serializable {


    private int id;
    @Size(min = 1, max = 100, message = "Aantal karakters moet tussen 1 en 240 zijn.")
    private String body;
    private Date postDate;
    private Story story;

    public int getId() {
        return id;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
