package be.intec.controllers;

import be.intec.entities.Location;
import be.intec.entities.Trip;
import be.intec.repositories.LocationRepository;
import be.intec.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    TripRepository tripRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @GetMapping(value = "trip/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Location> getLocationsByTrip(@PathVariable("id") int tripId) {
        Trip trip = tripRepository.findOne(tripId);
        return locationRepository.findAllByTrip(trip);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Location getLocationById(@PathVariable("id") int id) {
        return locationRepository.findOne(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addLocation(@RequestBody Location location) {
        locationRepository.save(location);
        return new ResponseEntity(location, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateLocation(@PathVariable("id") int id, @RequestBody Location location) {
        boolean locationExists = locationRepository.exists(id);
        if (locationExists) {
            location.setId(id);
            locationRepository.save(location);
            return new ResponseEntity(location, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteLink(@PathVariable("id") int id) {
        Location location = locationRepository.findOne(id);
        if (location != null) {
            locationRepository.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}