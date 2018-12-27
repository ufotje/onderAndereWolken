package be.intec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trip")
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "title", unique = true, nullable = false)
    private String title;
    @JsonIgnoreProperties("trips")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "country_trip",
            joinColumns = @JoinColumn(name = "tripId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "countryName", referencedColumnName = "name"))
    private List<Country> countries = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private boolean deleted;
    private boolean visible;
    private LocalDateTime latestEdit;
    private int likes;
    @OneToMany(mappedBy = "trip", fetch = FetchType.EAGER)
    @JsonBackReference(value = "tripStories")
    private List<Story> stories = new ArrayList<>();
    @OneToMany(mappedBy = "trip")
    @JsonBackReference(value = "tripMedia")
    private List<Media> media = new ArrayList<>();
    @OneToMany(mappedBy = "trip")
    @JsonBackReference(value = "tripLocations")
    private List<Location> locations = new ArrayList<>();


    public Trip() {
        super();
    }

    public Trip(List<Country> countries, String title, Date startDate, Date endDate) {
        this();
        this.countries = countries;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", countries='" + countries + '\'' +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", deleted=" + deleted +
                ", visible=" + visible +
                ", latestEdit=" + latestEdit +
                ", stories=" + stories +
                '}';
    }
}
