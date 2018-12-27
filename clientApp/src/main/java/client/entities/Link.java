package client.entities;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class Link implements Serializable {

    private int id;
    @Size(min = 1, max = 255, message = "Aantal karakters moet tussen 1 en 255 zijn.")
    private String linkUrl;
    @Size(max = 255, message = "Maximum aantal karakters is 255.")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", linkUrl='" + linkUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (id != link.id) return false;
        if (!linkUrl.equals(link.linkUrl)) return false;
        return description.equals(link.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + linkUrl.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

}
