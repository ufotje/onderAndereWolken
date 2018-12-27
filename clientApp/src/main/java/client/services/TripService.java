package client.services;

import client.entities.Trip;

import java.util.List;

public interface TripService {
    List<Trip> findAllTrips();
    List<Trip> findAllTripsByCountry(String country);
    List<Trip> findAllTripsBySearchCriteria(String searchTerms);
    Trip findByTitle(String title);
    Trip findById(int id);
    Trip addTrip(Trip trip);
    Trip updateTrip(Trip trip);
    Trip toggleVisibility(Trip trip);
    Trip removeTrip(Trip trip);

    List<Trip> findTrips(String order, String orderDirection, Integer size, boolean showAll);
    List<Trip> findTrips(boolean showAll);
    List<Trip> findTripsSortedByLatestEdit(Integer size, boolean showAll);
    List<Trip> findAllTripsSortedByLatestEdit(boolean showAll);
    List<Trip> sortByPopularity();
}
