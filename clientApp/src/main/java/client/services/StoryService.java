package client.services;

import client.entities.Story;
import client.entities.Tag;
import client.entities.Trip;

import java.sql.Date;
import java.util.List;

public interface StoryService {
    List<Story> findAllStories();
    List<Story> findAllStoriesByTrip(Trip trip);
    List<Story> findStoriesBySearchCriteriaTags(String searchTerms);
    List<Story> findStoriesBySearchCriteriaTitle(String searchTerms);
    List<Story> findStories(String order, String orderDirection, Integer size, boolean showAll);
    List<Story> findStoriesSortedByLatestEdit(Integer size, boolean showAll);
    List<Story> findAllStoriesSortedByLatestEdit(boolean showAll);

    Story findById(int id);
    Story findByTitle(String title);
    Story addStory(Story story);
    Story updateStory(int storyId, Story storyUpdates);
    Story updateStoryAllFields(Story story);
    Story toggleVisibility(Story story);
    Story removeStory(Story story);

    List<Tag> generateTagListFrom(String tags);
    String generateTagStringFrom(List<Tag> tags);
    String generateValidTeaser(String unvalidatedTeaser, String story);
    List <Story> findAllByOrderByLikesDesc();
}
