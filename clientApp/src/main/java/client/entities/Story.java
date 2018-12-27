package client.entities;

import client.services.search.LatestEditOrderable;
import sun.plugin2.message.Message;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Story implements Serializable, LatestEditOrderable {

    private int id;
    @Size(min = 1, max = 100, message = "1-100 karakters.")
    private String title;
    private String teaser;
    @Size(min = 1, message = "Niet ingevuld.")
    private String story;
    private String picUrl;
    @NotNull(message = "Vul datum in.")
    @Past(message = "Kan niet in de toekomst liggen.")
    private Date postDate;
    private double longitude;
    private double lattitude;
    private boolean visible;
    private boolean deleted;
    private LocalDateTime latestEdit;
    private Trip trip;
    private List<Tag> tags = new ArrayList();
    private List<Comment> comments = new ArrayList();
    private int likes;

    public Story() {
    }

    public Story(int id, String title, String teaser, String story, String picUrl, Date postDate, double longitude, double lattitude, boolean visible, boolean deleted, Trip tripId, List<Tag> tags, int likes) {
        setId(id);
        setTitle(title);
        setTeaser(teaser);
        setStory(story);
        setPicUrl(picUrl);
        setPostDate(postDate);
        setLongitude(longitude);
        setLattitude(lattitude);
        setVisible(visible);
        setDeleted(deleted);
        setTrip(tripId);
        setTags(tags);
        setLikes(likes);
    }

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

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getLatestEdit() {
        return latestEdit;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
                ", postDate=" + postDate +
                ", longitude=" + longitude +
                ", lattitude=" + lattitude +
                ", visible=" + visible +
                ", deleted=" + deleted +
                ", latestEdit=" + latestEdit +
                ", trip=" + trip +
                ", tags=" + tags +
                ", comments=" + comments +
                ", likes=" + likes +
                '}';
    }
}

