package be.intec.entities;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "newsLetterSubscription")
public class NewsLetter implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name="email", unique = true)
    @Email(message = "Gelieve een geldig email toe te voegen")
    @NotEmpty(message = "Gelieve een email toe te voegen")
    private String mail;
    public NewsLetter() {
    }
    public NewsLetter(String mail) {
        this.mail = mail;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}