package be.intec.services;

import be.intec.entities.Country;
import be.intec.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ManageSiteStatisticsImpl implements ManageSiteStatistics{
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private PictureRepository pictureRepository;

    public long countTrips() {
        return tripRepository.getTripsByDeletedIsFalse().size();
    }

    public long countVisitedCountries() {
        Set<Country> visitedCountries = new HashSet<>();
        tripRepository.findAllTripsJoinCountries().forEach(trip ->
                trip.getCountries().forEach(country -> visitedCountries.add(country)));
        return visitedCountries.size();
    }

    public long countPictures() {
        return pictureRepository.count();
    }

    public long countStories() {
        long totalStories = storyRepository.findAllByVisibleAndDeletedOrderByLikesDesc(true, false).size();
        totalStories += storyRepository.findAllByVisibleAndDeletedOrderByLikesDesc(false, false).size();
        return totalStories;
    }

    public long countMovies() {
        long totalMedia = mediaRepository.findAllByVisibleAndDeletedOrderByLikesDesc(true, false).size();
        totalMedia += mediaRepository.findAllByVisibleAndDeletedOrderByLikesDesc(false, false).size();
        return totalMedia;
    }
/*  public long countVisitors() {
    return ;
    }
 */

    public long countComments() {
        return commentRepository.findAll().size();
    }

}
