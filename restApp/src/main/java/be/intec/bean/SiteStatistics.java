package be.intec.bean;

import be.intec.repositories.CommentRepository;
import be.intec.repositories.StoryRepository;
import be.intec.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SiteStatistics {
    private long allTrips = 0;
    private long allVisitedCountries = 0;
    private long allPictures = 0;
    private long allStories = 0;
    private long allMovies = 0;
    private long allVisitors = 0;
    private long allComments = 0;

    public long getAllTrips() {
        return allTrips;
    }

    public void setAllTrips(long allTrips) {
        this.allTrips = allTrips;
    }

    public long getAllVisitedCountries() {
        return allVisitedCountries;
    }

    public void setAllVisitedCountries(long allVisitedCountries) {
        this.allVisitedCountries = allVisitedCountries;
    }

    public long getAllPictures() {
        return allPictures;
    }

    public void setAllPictures(long allPictures) {
        this.allPictures = allPictures;
    }

    public long getAllStories() {
        return allStories;
    }

    public void setAllStories(long allStories) {
        this.allStories = allStories;
    }

    public long getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(long allMovies) {
        this.allMovies = allMovies;
    }

    public long getAllVisitors() {
        return allVisitors;
    }

    public void setAllVisitors(long allVisitors) {
        this.allVisitors = allVisitors;
    }

    public long getAllComments() {
        return allComments;
    }

    public void setAllComments(long allComments) {
        this.allComments = allComments;
    }
}
