package client.entities;

import client.entities.validation.ValidDateAfterDate;
import client.services.search.LatestEditOrderable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ValidDateAfterDate(value = {"endDate","startDate"}, message = "De einddatum moet na de begindatum komen.")
public class Trip implements Serializable, LatestEditOrderable {

    private int id;
    private  List<Country> countries = new ArrayList<>();
    @NotNull
    @Pattern(regexp = "(?!^\\d+$)^.+$", message = "Een titel mag niet uit slechts één nummer bestaan.")
    @Size(min = 2, max = 100, message = "De lengte van een titel bedraagt tussen 2 en 100 karakters.")
    private String title;
    private Date startDate;
    private Date endDate;
    private boolean visible;
    private boolean deleted;
    private LocalDateTime latestEdit;
    private List<Story> stories;
    private List<String> photoAlbums;
    private int likes;

    public Trip(){
        super();
        this.stories = new ArrayList<>();
        this.photoAlbums = new ArrayList<>();
    }

    public Trip(List<Country> countries, String title, Date startDate, Date endDate) {
        this.countries = countries;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<String> getPhotoAlbums() {
        return photoAlbums;
    }

    public void setPhotoAlbums(List<String> photoAlbums) {
        this.photoAlbums = photoAlbums;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", countries='" + countries + '\'' +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", visible=" + visible +
                ", deleted=" + deleted +
                ", latestEdit=" + latestEdit +
                ", stories=" + stories +
                '}';
    }
}
