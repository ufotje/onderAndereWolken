package client.services.implementation;

import client.entities.Location;
import client.entities.Trip;
import client.services.LocationService;
import client.services.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component("LocationService")
public class LocationServiceImpl implements LocationService {

    private String baseUrl;
    private RestTemplate restTemplate;

    @Autowired
    private RestClientService restClientService;

    @Value("${resource.server.socket}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Location> findAllLocations() {
        ResponseEntity<Location[]> responseEntity = restTemplate.getForEntity(baseUrl + "locations", Location[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Location> findLocationsByTrip(Trip trip) {
        ResponseEntity<Location[]> responseEntity = restTemplate.getForEntity(baseUrl + "locations/trip/" + trip.getId(), Location[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public Location findById(int id) {
        return restTemplate.getForObject(baseUrl + "locations/{0}", Location.class, id);
    }

    @Override
    public Location addLocation(Location location) {
        ResponseEntity<Location> responseEntity = restClientService.authenticatedExchange(baseUrl + "locations", HttpMethod.POST, location, Location.class);
        return responseEntity.getBody();
    }

    @Override
    public Location updateLocation(Location location) {
        ResponseEntity<Location> responseEntity = restClientService.authenticatedExchange(baseUrl + "locations/" + location.getId(), HttpMethod.PUT, location, Location.class);
        return responseEntity.getBody();
    }

    @Override
    public Location deleteLocation(Location location) {
        ResponseEntity<Location> responseEntity = restClientService.authenticatedExchange(baseUrl + "locations/" + location.getId(), HttpMethod.DELETE, location, Location.class);
        return responseEntity.getBody();
    }

}
