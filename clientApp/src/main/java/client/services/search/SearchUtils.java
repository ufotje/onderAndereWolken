package client.services.search;

import client.entities.Trip;
import client.exceptions.PageNotFoundException;
import client.services.MediaService;
import client.services.StoryService;
import client.services.TripService;

import java.util.*;
import java.util.stream.Collectors;

public class SearchUtils {
    public static final String STORY = "story";
    public static final String TRIP = "trip";
    public static final String MEDIA = "media";


    private SearchUtils() {
        super();
    }

    public static Map<LatestEditOrderable, String> limit(Map<LatestEditOrderable, String> allElements, int page, int elementsOnPage) {
        int offset = page - 1;
        if(offset >= 0 && (offset * elementsOnPage < allElements.size())) {
            offset *= elementsOnPage;
        } else {
            throw new PageNotFoundException();
        }
        return allElements.entrySet().stream().skip(offset).limit(elementsOnPage).collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
    }

    public static String firstMiddleLastOrOnlyPage(Map<LatestEditOrderable, String> allElements, int page, int elementsOnPage) {
        if(((page - 1) < 1) && (((page - 1) * elementsOnPage + elementsOnPage) >= allElements.size())) {
            return "only";
        } else if((page - 1) < 1) {
            return "first";
        } else if(((page - 1) * elementsOnPage + elementsOnPage) >= allElements.size()) {
            return "last";
        } else {
            return "middle";
        }
    }

    public static Map<LatestEditOrderable, String> searchMainPageItemsByTitle(TripService tripService, StoryService storyService, MediaService mediaService, String searchTerms) {
        Map<LatestEditOrderable, String> unorderedSearchResults = new LinkedHashMap<>();
        tripService.findAllTripsBySearchCriteria(searchTerms).forEach(trip -> unorderedSearchResults.put(trip, TRIP));
        storyService.findStoriesBySearchCriteriaTitle(searchTerms).forEach(story -> unorderedSearchResults.put(story, STORY));
        mediaService.findAllMediaBySearchCriteriaTitle(searchTerms).forEach(media -> unorderedSearchResults.put(media, MEDIA));
        return unorderedSearchResults.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(LatestEditOrderable::getLatestEdit).reversed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public static Map<LatestEditOrderable, String> searchStoriesTags(StoryService storyService, Map<LatestEditOrderable, String> dataCollection, String searchTerms) {
        storyService.findStoriesBySearchCriteriaTags(searchTerms).forEach(story -> dataCollection.put(story, STORY));
        return dataCollection;
    }

    public static Map<LatestEditOrderable, String> orderMainPageItemsByPopularity(TripService tripService, StoryService storyService, MediaService mediaService){
        Map<LatestEditOrderable, String> unorderedSearchResults = new LinkedHashMap<>();
        tripService.sortByPopularity().forEach(trip -> unorderedSearchResults.put(trip, TRIP));
        storyService.findAllByOrderByLikesDesc().forEach(story -> unorderedSearchResults.put(story, STORY));
        mediaService.sortByPopularity().forEach(media -> unorderedSearchResults.put(media, MEDIA));
        return unorderedSearchResults.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(LatestEditOrderable::getLikes).reversed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public static Map<LatestEditOrderable, String> showTripsByCountry(TripService tripService, String countryName) {
        Map<LatestEditOrderable, String> unorderedSearchResults = new LinkedHashMap<>();
        List<Trip> trips = tripService.findAllTripsByCountry(countryName);
        trips.forEach(trip -> unorderedSearchResults.put(trip, TRIP));
        return unorderedSearchResults.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(LatestEditOrderable::getLatestEdit).reversed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public static Map<LatestEditOrderable, String> showStoriesByCountry(TripService tripService, StoryService storyService, String countryName) {
        Map<LatestEditOrderable, String> unorderedSearchResults = new LinkedHashMap<>();
        List<Trip> trips = tripService.findAllTripsByCountry(countryName);
        trips.forEach(trip -> storyService.findAllStoriesByTrip(trip)
                .forEach(story -> unorderedSearchResults.put(story, STORY)));
        return unorderedSearchResults.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(LatestEditOrderable::getLatestEdit).reversed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public static Map<LatestEditOrderable, String> showMediaByCountry(MediaService mediaService, TripService tripService, String countryName) {
        Map<LatestEditOrderable, String> unorderedSearchResults = new LinkedHashMap<>();
        List<Trip> trips = tripService.findAllTripsByCountry(countryName);
        trips.forEach(trip -> mediaService.findAllMediaByTrip(trip)
                .forEach(media -> unorderedSearchResults.put(media, MEDIA)));
        return unorderedSearchResults.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(LatestEditOrderable::getLatestEdit).reversed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

}
