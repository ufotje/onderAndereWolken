package be.intec.entities;

/**
 * Created by PearlS on 9/11/2017.
 */
public class TripLike {

    private Trip trip;
    private long tripLikes;

    public TripLike() {
    }


    public TripLike(Trip trip, long tripLikes) {
        this.trip = trip;
        this.tripLikes = tripLikes;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public long getTripLikes() {
        return tripLikes;
    }

    public void setTripLikes(long tripLikes) {
        this.tripLikes = tripLikes;
    }
}
