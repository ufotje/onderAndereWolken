package client.services;

import client.entities.Location;
import client.entities.Trip;

import java.util.List;

public interface LocationService {

    List<Location> findAllLocations();

    List<Location> findLocationsByTrip(Trip trip);

    Location findById(int id);

    Location addLocation(Location location);

    Location updateLocation(Location location);

    Location deleteLocation(Location location);

}
