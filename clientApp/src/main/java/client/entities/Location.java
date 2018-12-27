package client.entities;

import java.io.Serializable;

public class Location implements Serializable {

    private int id;
    private String latitude;
    private String longitude;
    private Trip trip;

    public Location() {
    }

    public Location(int id, String latitude, String longitude, Trip trip) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trip = trip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", trip=" + trip +
                '}';
    }

}
