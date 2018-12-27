package client.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

public class Album implements Serializable {

    private int id;
    private String title;
    private String description;
    private Picture[] pictureLocations;
    private Date postDate;
    private LocalDateTime latestEdit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Picture[] getPictureLocations() {
        return pictureLocations;
    }

    public void setPictureLocations(Picture[] pictureLocations) {
        this.pictureLocations = pictureLocations;
    }

    public LocalDateTime getLatestEdit() {
        return latestEdit;
    }

    public void setLatestEdit(LocalDateTime latestEdit) {
        this.latestEdit = latestEdit;
    }
}
