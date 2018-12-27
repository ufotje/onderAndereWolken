package be.intec.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Created by PearlS on 13/11/2017.
 */

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue
    private int id;
    @Size(min = 1, max = 240, message = "Aantal karakters moet tussen 1 en 240 zijn.")
    private String body;
    @Size(min = 1, max = 25, message = "Aantal karakters moet tussen 1 en 25 zijn.")
    private String name;
    @Column(name = "postDate")
    private LocalDateTime postDate;
    @Email(message = "Gelieve een geldig email toe te voegen")
    @NotEmpty(message = "Gelieve een email toe te voegen")
    private String eMail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", name='" + name + '\'' +
                ", postDate=" + postDate +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
