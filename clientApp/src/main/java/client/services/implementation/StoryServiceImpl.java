package client.services.implementation;

import client.entities.Story;
import client.entities.Tag;
import client.entities.Trip;
import client.services.StoryService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component("StoryService")
public class StoryServiceImpl implements StoryService {
    private String baseUrl;
    private RestTemplate template;

    @Value("${resource.server.socket}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Autowired
    public void setTemplate(RestTemplate template) {
        this.template = template;
    }

    @Autowired
    private RestClientServiceImpl restClientService;

    @Override
    public List<Story> findAllStories() {
        ResponseEntity<Story[]> response = template.getForEntity(baseUrl + "stories", Story[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<Story> findAllStoriesByTrip(Trip trip) {
        ResponseEntity<Story[]> responseEntity = template.getForEntity(baseUrl + "stories/trip/" + trip.getId(), Story[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Story> findStoriesBySearchCriteriaTags(String searchTerms) {
        ResponseEntity<Story[]> responseEntity = template.getForEntity(baseUrl + "stories?searchTag={0}", Story[].class, searchTerms);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Story> findStoriesBySearchCriteriaTitle(String searchTerms) {
        ResponseEntity<Story[]> responseEntity = template.getForEntity(baseUrl + "stories?search={0}", Story[].class, searchTerms);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Story> findStories(String order, String orderDirection, Integer size, boolean showAll) {
        StringBuilder url = new StringBuilder(baseUrl + "/stories?");
        if(isNotBlank(order)) url.append("order=" + order + "&");
        if(orderDirection.equalsIgnoreCase("asc") || orderDirection.equalsIgnoreCase("desc"))
            url.append("orderDirection=" + orderDirection + "&");
        if(size != null) url.append("size=" + size.intValue() + "&");
        if(showAll) url.append("showAll&");

        ResponseEntity<Story[]> responseEntity = template.getForEntity(url.toString(), Story[].class);

        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Story> findStoriesSortedByLatestEdit(Integer size, boolean showAll) {
        return findStories("latestEdit", "desc", size, showAll);
    }

    @Override
    public List<Story> findAllStoriesSortedByLatestEdit(boolean showAll) {
        return findStoriesSortedByLatestEdit(null, showAll);
    }

    @Override
    public Story findById(int id) {
        return template.getForObject(baseUrl + "stories/{0}", Story.class, id);
    }

    @Override
    public Story findByTitle(String title) {
        return template.getForObject(baseUrl + "stories/{0}", Story.class, title);
    }

    @Override
    public Story addStory(Story story) {
        ResponseEntity<Story> responseEntity = restClientService.authenticatedExchange(baseUrl+"stories", HttpMethod.POST, story, Story.class);
        return responseEntity.getBody();
    }

    @Override
    public Story updateStory(int storyId, Story storyUpdates) {
        Story story = findById(storyId);
        if(story == null) return null;
        story.setTrip(storyUpdates.getTrip());
        story.setTitle(storyUpdates.getTitle());
        story.setTeaser(generateValidTeaser(storyUpdates.getTeaser(), storyUpdates.getStory()));
        story.setStory(storyUpdates.getStory());
        story.setPicUrl(storyUpdates.getPicUrl());
        story.setPostDate(storyUpdates.getPostDate());
        story.setTags(storyUpdates.getTags());
        story.setVisible(storyUpdates.isVisible());
        story.setLikes(storyUpdates.getLikes());
        ResponseEntity<Story> responseEntity = restClientService.authenticatedExchange(baseUrl+"stories/"+storyId, HttpMethod.PUT, story, Story.class);
        return responseEntity.getBody();
    }

    @Override
    public Story updateStoryAllFields(Story story) {
        ResponseEntity<Story> responseEntity = restClientService.authenticatedExchange(baseUrl+"stories/"+story.getId(), HttpMethod.PUT, story, Story.class);
        return responseEntity.getBody();
    }

    @Override
    public Story toggleVisibility(Story story) {
        ResponseEntity<Story> responseEntity = restClientService.authenticatedExchange(baseUrl+"stories/"+story.getId()+"?toggleVisibility", HttpMethod.PUT, story, Story.class);
        return responseEntity.getBody();
    }

    @Override
    public Story removeStory(Story story) {
        ResponseEntity<Story> responseEntity = restClientService.authenticatedExchange(baseUrl + "stories/" + story.getId(), HttpMethod.DELETE, Story.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Tag> generateTagListFrom(String tags) {
        String[] splitTags = tags.split(",");
        List<Tag> storyTags = new ArrayList<>();
        for (String tag : splitTags) {
            Tag newTag = new Tag();
            newTag.setWord(tag);
            storyTags.add(newTag);
        }
        return storyTags;
    }

    @Override
    public String generateTagStringFrom(List<Tag> tags) {
        StringBuilder tagsBuilder = new StringBuilder();
        for(int i = 0; i < tags.size(); i++) {
            tagsBuilder.append(tags.get(i));
            if(i < (tags.size() - 1)) tagsBuilder.append(",");
        }
        return tagsBuilder.toString();
    }

    @Override
    public String generateValidTeaser(String customTeaser, String story) {
        if (customTeaser != null) {
            customTeaser = Jsoup.clean(customTeaser, Whitelist.simpleText()).trim();
        }
        if(isBlank(customTeaser) || customTeaser.length() > 300) {
            story = Jsoup.clean(story, Whitelist.simpleText()).trim();
            if(story.length() > 300) {
                story = story.substring(0, 297);
                story += "...";
            }
            return story;
        } else {
            return customTeaser;
        }
    }

    @Override
    public List<Story> findAllByOrderByLikesDesc() {
        ResponseEntity<Story[]> response = template.getForEntity(baseUrl + "stories/popular", Story[].class);
        return Arrays.asList(response.getBody());
    }
}
