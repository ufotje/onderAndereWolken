package client.entities;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;


public class NewsLetter implements Serializable {

    private int id;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "NewsLetter{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                '}';
    }
}