package be.intec.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue
    private int id;
    private String location;
    @ManyToMany
    private List<Album> album;

    public Picture(){
    }

    public Picture(String location, List<Album> album){
        setLocation(location);
        setAlbum(album);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
