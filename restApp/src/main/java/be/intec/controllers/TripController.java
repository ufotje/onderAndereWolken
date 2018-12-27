package be.intec.controllers;

import be.intec.entities.Country;
import be.intec.entities.Trip;
import be.intec.repositories.CountryRepository;
import be.intec.entities.TripLike;
import be.intec.repositories.MediaRepository;
import be.intec.repositories.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import be.intec.repositories.TripRepository;

import javax.servlet.http.HttpServletResponse;

import static be.intec.repositories.implementation.CommonSpecification.searchTitleContainsIgnoreCase;
import static be.intec.repositories.implementation.CommonSpecification.limitAndOrSort;
import static be.intec.repositories.implementation.CommonSpecification.filterVisibilityAndOptionalWhere;

import java.time.LocalDateTime;
import java.util.*;

import static be.intec.repositories.implementation.CommonSpecification.joinSpecifications;

@RestController
@RequestMapping("trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private MediaRepository mediaRepository;


    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Trip> getAllTripsPossibleParams(@RequestParam Map<String, String> readCriteria, HttpServletResponse response){
        return tripRepository.findAll(filterVisibilityAndOptionalWhere(readCriteria, Trip.class), limitAndOrSort(readCriteria)).getContent();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Trip getTripById(@PathVariable("id") int id, HttpServletResponse response) {
        Trip trip = tripRepository.findOne(id);
        return trip;
    }

    @GetMapping(params = "search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<Trip> getSearchResultsTrip(@RequestParam("search") String search, @RequestParam Map<String, String> readCriteria) {
        Set<Trip> tripsByTitle = new LinkedHashSet<>(tripRepository.findAll(joinSpecifications(searchTitleContainsIgnoreCase(Trip.class, search.split(" ")), filterVisibilityAndOptionalWhere(readCriteria, Trip.class))));
        return tripsByTitle;
    }

    @GetMapping(value = "popular/trips", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Trip>getByMostPopular(){
        List<TripLike> tripLikes = tripRepository.sortByPopularity();
        List<Trip> trips = new ArrayList<>();
        for (TripLike tripLike: tripLikes){
            Trip trip = tripLike.getTrip();
            trip.setLikes((int) tripLike.getTripLikes());
            trips.add(trip);
        }
        return trips ;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addTrip(@RequestBody Trip trip) {
        Set<Country> countries = new HashSet<>();
        for(Country countryGiven : trip.getCountries()) {
            countries.add(countryRepository.findCountryByName(countryGiven.getName()));
        }
        countries.remove(null);
        trip.setCountries(new ArrayList<>(countries));
        trip.setLatestEdit(LocalDateTime.now());
        tripRepository.save(trip);
        cascadeChildrenVisibility(trip);
        return new ResponseEntity(trip, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateTrip(@PathVariable("id") int id, @RequestBody Trip trip) {
        boolean exists = tripRepository.exists(id);
        if(exists) {
            trip.setId(id);
            trip.setLatestEdit(LocalDateTime.now());

            Set<Country> countries = new HashSet<>();
            for(Country countryGiven : trip.getCountries()) {
                countries.add(countryRepository.findCountryByName(countryGiven.getName()));
            }
            countries.remove(null);
            trip.setCountries(new ArrayList<>(countries));

            tripRepository.save(trip);
            cascadeChildrenVisibility(trip);
            return new ResponseEntity(trip, HttpStatus.OK);
        }
        return new ResponseEntity("Geen trip gevonden met id: " + id, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}", params = {"toggleVisibility"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateToggleTripVisibility(@PathVariable("id") int id) {
        Trip trip = tripRepository.findOne(id);
        if(trip != null) {
            trip.setVisible(!trip.isVisible());
            tripRepository.save(trip);
            cascadeChildrenVisibility(trip);
            return new ResponseEntity(trip, HttpStatus.OK);
        }
        return new ResponseEntity("Geen trip gevonden met id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteTrip(@PathVariable("id") int id) {
        Trip trip = tripRepository.findOne(id);
        if(trip != null) {
            trip.setDeleted(true);
            tripRepository.save(trip);
            deletesChildren(trip);
        }
        return new ResponseEntity(trip, HttpStatus.OK);
    }

    public void cascadeChildrenVisibility(Trip trip) {
        if(!trip.isVisible()) {
            storyRepository.findStoryByTrip(trip).forEach(story -> {story.setVisible(false);
                                                                    storyRepository.save(story);});
            mediaRepository.findMediaByTrip(trip).forEach(media -> {media.setVisible(false);
                                                                    mediaRepository.save(media);});
            /*albumRepository.findAlbumByTrip(trip).forEach(album -> {album.setVisible(false);
                                                                    albumRepository.save(album);});*/
        }
    }

    public void deletesChildren(Trip trip) {
        storyRepository.findStoryByTrip(trip).forEach(story -> {story.setDeleted(true);
                                                                storyRepository.save(story);});
        mediaRepository.findMediaByTrip(trip).forEach(media -> {media.setDeleted(true);
                                                                mediaRepository.save(media);});
        /*albumRepository.findAlbumByTrip(trip).forEach(album -> {album.setVisible(false);
                                                                albumRepository.save(album);});*/
    }

}
