package client.entities;

import client.services.search.LatestEditOrderable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

public class Media implements Serializable, LatestEditOrderable {

    private int id;
    @Size(min = 1, max = 100, message = "1-100 karakters.")
    private String title;
    private Trip trip;
    @Size(min = 1, message = "Niet ingevuld.")
    private String url;
    @NotNull(message = "Vul datum in.")
    @Past(message = "Kan niet in de toekomst liggen.")
    private Date postDate;
    private LocalDateTime latestEdit;
    private boolean visible;
    private boolean deleted;
    private int likes;

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

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Override
    public LocalDateTime getLatestEdit() {
        return this.latestEdit;
    }

    public void setLatestEdit(LocalDateTime latestEdit) {
        this.latestEdit = latestEdit;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int getLikes() {
        return this.likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", trip=" + trip +
                ", url='" + url + '\'' +
                ", postDate=" + postDate +
                ", latestEdit=" + latestEdit +
                ", visible=" + visible +
                ", deleted=" + deleted +
                ", likes=" + likes +
                '}';
    }
}
