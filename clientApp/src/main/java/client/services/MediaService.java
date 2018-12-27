package client.services;

import client.entities.Media;
import client.entities.Trip;

import java.util.List;

public interface MediaService {
    List<Media> findAllMedia();
    List<Media> findAllMediaByTrip(Trip trip);
    List<Media> findAllMediaBySearchCriteriaTitle(String searchTerms);
    List<Media> findMediaSortedByLatestEdit(Integer size, boolean showAll);
    List<Media> findAllMediaSortedByLatestEdit(boolean showAll);
    List<Media> findMedia(String order, String orderDirection, Integer size, boolean showAll);

    List<Media> sortByPopularity();

    Media findByTitle(String title);
    Media findById(int id);
    Media addMedia(Media media);
    Media updateMedia(int mediaId, Media mediaUpdates);
    Media updateMediaAllFields(Media media);
    Media toggleVisibility(Media media);
    Media removeMedia(Media media);
}
