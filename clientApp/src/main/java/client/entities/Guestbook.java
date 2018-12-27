package client.entities;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Guestbook implements Serializable {

    private int id;
    @Size(min = 1, max = 255, message = "Aantal karakters moet tussen 1 en 255 zijn.")
    private String name;
    @Email(message = "Gelieve een geldig e-mailadres in te voeren.")
    @Size(min = 1, message = "Gelieve een geldig e-mailadres in te voeren.")
    private String email;
    @Size(min = 1, max = 255, message = "Aantal karakters moet tussen 1 en 255 zijn.")
    private String message;
    private LocalDateTime postDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "Guestbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", postDate=" + postDate +
                '}';
    }

}
