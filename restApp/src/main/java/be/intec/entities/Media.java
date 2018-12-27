package be.intec.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "title" ,unique = true, nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name="tripId")
    private Trip trip;
    private String url;
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

    public LocalDateTime getLatestEdit() {
        return latestEdit;
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

    public int getLikes() {
        return likes;
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
                '}';
    }
}
