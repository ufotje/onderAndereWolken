package be.intec.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "flag")
    private String flagUrl;
    @JsonIgnoreProperties("countries")
    @ManyToMany (mappedBy = "countries", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Trip> trips = new ArrayList<>();


    public Country() {
        super();
    }

    public Country(String name, String flagUrl) {
        this();
        setName(name);
        setFlagUrl(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void addTrip(Trip trip) {
        if (!getTrips().contains(trip)) {
            getTrips().add(trip);
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '}';
    }
}
