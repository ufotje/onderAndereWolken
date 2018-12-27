package be.intec.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DanielD on 6/10/2017.
 */

@Entity
@Table(name = "story")
public class Story {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "teaser")
    private String teaser;
    @Column(name = "story")
    private String story;
    @Column(name = "picture")
    private String picUrl;
    @Column(name = "postDate")
    private Date postDate;
    private double longitude;
    private double lattitude;
    private boolean visible;
    private LocalDateTime latestEdit;
    private boolean deleted;
    @ManyToOne
    @JoinColumn(name="tripId")
    private Trip trip;
    @JsonIgnoreProperties("stories")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "story_tag",
            joinColumns = @JoinColumn(name = "storyId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tagId", referencedColumnName = "id"))
    private List<Tag> tags = new ArrayList();
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

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date date) {
        this.postDate = date;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public LocalDateTime getLatestEdit() {
        return latestEdit;
    }

    public void setLatestEdit(LocalDateTime latestEdit) {
        this.latestEdit = latestEdit;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", teaser='" + teaser + '\'' +
                ", story='" + story + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", date=" + postDate +
                ", longitude=" + longitude +
                ", lattitude=" + lattitude +
                ", visible=" + visible +
                ", deleted=" + deleted +
                ", tripId=" + trip +
                ", tags=" + tags +
                ", likes=" + likes +
                '}';
    }
}

