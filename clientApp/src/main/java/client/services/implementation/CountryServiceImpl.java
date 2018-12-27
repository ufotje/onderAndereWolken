package client.services.implementation;

import client.entities.Country;
import client.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component("CountryService")
public class CountryServiceImpl implements CountryService{
    private String baseUrl;
    private RestTemplate template;

    @Value("${resource.server.socket}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Autowired
    public void setTemplate(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<Country> findAllCountries() {
        ResponseEntity<Country[]> response = template.getForEntity(baseUrl + "/countries", Country[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<Country> findAllCountriesContainingActiveTrips() {
        ResponseEntity<Country[]> response = template.getForEntity(baseUrl + "/countries?visited", Country[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Country findCountry(String name) {
        return template.getForObject(baseUrl + "countries/{name}", Country.class, name);
    }


}
