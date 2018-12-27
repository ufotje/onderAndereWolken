package be.intec.controllers;

import be.intec.entities.Country;
import be.intec.entities.Trip;
import be.intec.repositories.CountryRepository;
import be.intec.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private TripRepository tripRepository;



    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    @GetMapping(params = "visited", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Country> getCountriesContainingTrips(){
        // Todo put logic in different class reusable by site statistics
        List<Country> countries = countryRepository.findAll();
        for(int i = 0; i < countries.size(); i++) {
            boolean countryContainsActiveTrips = false;
            List<Trip> trips = tripRepository.findAllTripsByCountries(Collections.singletonList(countries.get(i)));
            for(Trip trip : trips) {
                if((!trip.isDeleted()) && (trip.isVisible())) countryContainsActiveTrips = true;
            }
            if(!countryContainsActiveTrips) countries.remove(i);
        }
        return countries;
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Country getCountry(@PathVariable("name") String name){
        return countryRepository.findCountryByName(name);
    }

    @GetMapping(params = {"name"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Trip> getTripsByCountry(@RequestParam("name") String countryName, @RequestParam(value = "type", required = false) String trip) {
        if(StringUtils.hasText(trip) && !trip.equalsIgnoreCase("trip")) return null;
        return tripRepository.findAllTripsByCountries(Collections.singletonList(countryRepository.findCountryByName(countryName)));
    }

    /*@GetMapping(params = {"name", "type=trip"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<Trip> getTripsByCountryb(@RequestParam("name") String countryName, @RequestParam("type", "") String trip) {
        tripRepository.findByCountries();
    }*/


    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Country handleAddCountry(@RequestBody Country country, HttpServletRequest request) {
        country.setFlagUrl(validateFlag(request, country.getFlagUrl()));
        return countryRepository.save(country);
    }

    @PutMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Country handleUpdateCountry(@RequestBody Country countryChanges, @PathVariable("name") String name,
                                       HttpServletRequest request) {
        Country country = countryRepository.findCountryByName(name);
        country.setFlagUrl(validateFlag(request, countryChanges.getFlagUrl()));
        return countryRepository.save(country);
    }


    // Reminder flag urls from website: http://www.flagsinformation.com/chinese-flag.png
    private String validateFlag(HttpServletRequest request, String flagUrl) {
        return (StringUtils.hasText(flagUrl)) ? flagUrl : (request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath())  + "/images/clouds.jpg");
    }

}
