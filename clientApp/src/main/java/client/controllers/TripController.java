package client.controllers;

import client.entities.Location;
import client.entities.Trip;
import client.services.LocationService;
import client.services.search.LatestEditOrderable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import client.services.TripService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static client.config.GlobalVariables.REGEX_NUMBER;
import static client.services.search.SearchUtils.showTripsByCountry;
import static client.controllers.services.Pagination.pagingJsp;

@Controller
@RequestMapping("trips")
public class TripController {
    @Autowired
    private TripService tripService;

    @Autowired
    private LocationService locationService;

    private int ITEMS_ON_PAGE = 8;

    @GetMapping
    public String getAllTripsMainPage(ModelMap model) {
        return getAllTripsPages("1", model);
    }

    @GetMapping(params = "pagina")
    public String getAllTripsPages(@RequestParam("pagina") String page, ModelMap model) {
        Map<LatestEditOrderable, String> trips = new LinkedHashMap<>();
        tripService.findAllTrips().forEach(trip -> trips.put(trip, "trip"));
        pagingJsp(trips, page, ITEMS_ON_PAGE, "/trips?pagina=", model);
        return "trips";
    }

    @GetMapping(value = "/{identifier}")
    public String getTripByIdOrTitle(Model model, @PathVariable("identifier") String tripIdentifier) {
        Trip trip = (tripIdentifier.matches(REGEX_NUMBER)) ? (tripService.findById(Integer.valueOf(tripIdentifier))) : (tripService.findByTitle(tripIdentifier));
        List<Location> tripLocations = locationService.findLocationsByTrip(trip);
        model.addAttribute("trip", trip);
        model.addAttribute("tripLocations", tripLocations);
        return "trip";
    }

    @GetMapping(params = {"land"})
    public String getAllTripsByCountry(@RequestParam("land") String country, ModelMap model) {
        return showForCountries(country, "1", model);
    }

    @GetMapping(params = {"land", "pagina"})
    public String showForCountries(@RequestParam("land")String countryName, @RequestParam("pagina") String page, ModelMap model) {
        Map<LatestEditOrderable, String> tripsForCountry = showTripsByCountry(tripService, countryName);
        pagingJsp(tripsForCountry, page, ITEMS_ON_PAGE, "/trips?land=" + countryName +"&pagina=", model);
        model.addAttribute("tripsForCountry", countryName);
        return "trips";
    }

}
