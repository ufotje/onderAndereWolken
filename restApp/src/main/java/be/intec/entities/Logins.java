package be.intec.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "persistent_logins")
public class Logins {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Column(name = "username")
    private String username;
    @NotNull
    @Column(name = "series")
    private String series;
    @NotNull
    @Column(name = "token")
    private String token;
    @NotNull
    @Column(name = "last_used")
    private Timestamp lastUsed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Timestamp lastUsed) {
        this.lastUsed = lastUsed;
    }
}
