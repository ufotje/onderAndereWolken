package client.entities;

import java.io.Serializable;
import java.util.List;

public class Picture implements Serializable {

    private int id;
    private String location;
    private List<Album> album;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
