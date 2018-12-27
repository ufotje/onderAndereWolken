package client.entities;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    private String flagUrl;
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


    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", flagUrl='" + flagUrl + '\'' +
                ", trips=" + trips +
                '}';
    }
}
