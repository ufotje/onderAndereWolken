package client.services.implementation;

import client.entities.Trip;
import client.services.RestClientService;
import client.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component("TripService")
public class TripServiceImpl implements TripService {
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

    @Autowired
    private RestClientService restClientService;

    @Override
    public List<Trip> findAllTrips() {
        ResponseEntity<Trip[]> responseEntity = template.getForEntity(baseUrl + "trips", Trip[].class);
        return (List<Trip>) Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Trip> findAllTripsByCountry(String country) {
        ResponseEntity<Trip[]> responseEntity = template.getForEntity(baseUrl + "/countries?name={0}", Trip[].class, country);
        return (List<Trip>) Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Trip> findAllTripsBySearchCriteria(String searchTerms) {
        ResponseEntity<Trip[]> responseEntity = template.getForEntity(baseUrl + "trips?search={0}", Trip[].class, searchTerms);
        return (List<Trip>) Arrays.asList(responseEntity.getBody());
    }

    @Override
    public Trip findByTitle(String title) {
        return template.getForObject(baseUrl + "trips/{0}", Trip.class, title);
    }

    @Override
    public Trip findById(int id) {
        return template.getForObject(baseUrl + "trips/{0}", Trip.class, id);
    }

    @Override
    public Trip addTrip(Trip trip) {
        ResponseEntity<Trip> responseEntity = restClientService.authenticatedExchange(baseUrl+"trips/", HttpMethod.POST, trip, Trip.class);
        return responseEntity.getBody();
    }

    @Override
    public Trip updateTrip(Trip trip) {
        ResponseEntity<Trip> responseEntity = restClientService.authenticatedExchange(baseUrl+"trips/"+trip.getId(), HttpMethod.PUT, trip, Trip.class);
        return responseEntity.getBody();
    }

    @Override
    public Trip toggleVisibility(Trip trip) {
        ResponseEntity<Trip> responseEntity = restClientService.authenticatedExchange(baseUrl+"trips/"+trip.getId()+"?toggleVisibility", HttpMethod.PUT, trip, Trip.class);
        return responseEntity.getBody();
    }

    @Override
    public Trip removeTrip(Trip trip) {
        ResponseEntity<Trip> responseEntity = restClientService.authenticatedExchange(baseUrl + "trips/" + trip.getId(), HttpMethod.DELETE, Trip.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Trip> findTrips(String order, String orderDirection, Integer size, boolean showAll) {
        StringBuilder url = new StringBuilder(baseUrl + "/trips?");
        if(isNotBlank(order)) url.append("order=" + order + "&");
        if(orderDirection != null && (orderDirection.equalsIgnoreCase("asc") || orderDirection.equalsIgnoreCase("desc"))) url.append("orderDirection=" + orderDirection + "&");
        if(size != null) url.append("size=" + size.intValue() + "&");
        if(showAll) url.append("showAll&");

        ResponseEntity<Trip[]> responseEntity = template.getForEntity(url.toString(), Trip[].class);
        //totalAmountTripsInDb = Long.valueOf(responseEntity.getHeaders().get("Amount-All-Trips").get(0));  // -> line is experimenting with site statistics

        return (List<Trip>) Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Trip> findTrips(boolean showAll) {
        return findTrips(null, null, null, showAll);
    }

    @Override
    public List<Trip> findTripsSortedByLatestEdit(Integer size, boolean showAll) {
        return findTrips("latestEdit", "desc", size, showAll);
    }

    @Override
    public List<Trip> findAllTripsSortedByLatestEdit(boolean showAll) {
        return findTripsSortedByLatestEdit(null, showAll);
    }

    @Override
    public List<Trip> sortByPopularity() {
        ResponseEntity<Trip[]> responseEntity = template.getForEntity(baseUrl + "trips/popular/trips", Trip[].class);
        return (List<Trip>) Arrays.asList(responseEntity.getBody());
    }

}
