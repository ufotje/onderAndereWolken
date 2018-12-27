package client.services.implementation;

import client.entities.Media;
import client.entities.Trip;
import client.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component("MediaService")
public class MediaServiceImpl implements MediaService{
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
    public List<Media> findAllMedia() {
        ResponseEntity<Media[]> response = template.getForEntity(baseUrl + "media", Media[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<Media> findAllMediaBySearchCriteriaTitle(String searchTerms) {
        ResponseEntity<Media[]> responseEntity = template.getForEntity(baseUrl + "media?search={0}", Media[].class, searchTerms);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Media> findMediaSortedByLatestEdit(Integer size, boolean showAll) {
        return findMedia("latestEdit", "desc", size, showAll);
    }

    @Override
    public List<Media> findAllMediaSortedByLatestEdit(boolean showAll) {
        return findMediaSortedByLatestEdit(null, showAll);
    }

    @Override
    public List<Media> findAllMediaByTrip(Trip trip) {
        ResponseEntity<Media[]> responseEntity = template.getForEntity(baseUrl + "media/trip/" + trip.getTitle(), Media[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Media> findMedia(String order, String orderDirection, Integer size, boolean showAll) {
        StringBuilder url = new StringBuilder(baseUrl + "/media?");
        if(isNotBlank(order)) url.append("order=" + order + "&");
        if(orderDirection.equalsIgnoreCase("asc") || orderDirection.equalsIgnoreCase("desc"))
            url.append("orderDirection=" + orderDirection + "&");
        if(size != null) url.append("size=" + size.intValue() + "&");
        if(showAll) url.append("showAll&");

        ResponseEntity<Media[]> responseEntity = template.getForEntity(url.toString(), Media[].class);

        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public Media findByTitle(String title) {
        ResponseEntity<Media> response = template.getForEntity (baseUrl + "media/{0}", Media.class, title);
        return response.getBody();
    }

    @Override
    public Media findById(int id) {
        ResponseEntity<Media> response = template.getForEntity (baseUrl + "media/{0}", Media.class, id);
        return response.getBody();
    }

    @Override
    public Media addMedia(Media media) {
        ResponseEntity<Media> responseEntity = restClientService.authenticatedExchange(baseUrl+"media/", HttpMethod.POST, media, Media.class);
        return responseEntity.getBody();
    }

    @Override
    public Media updateMedia(int mediaId, Media mediaUpdates) {
        Media media = findById(mediaId);
        if(media == null) return null;
        media.setTrip(mediaUpdates.getTrip());
        media.setTitle(mediaUpdates.getTitle());
        media.setUrl(mediaUpdates.getUrl());
        media.setPostDate(mediaUpdates.getPostDate());
        media.setVisible(mediaUpdates.isVisible());
        media.setLikes(mediaUpdates.getLikes());
        ResponseEntity<Media> responseEntity = restClientService.authenticatedExchange(baseUrl+"media/" + mediaId, HttpMethod.PUT, media, Media.class);
        return responseEntity.getBody();
    }

    @Override
    public Media updateMediaAllFields(Media media) {
        ResponseEntity<Media> responseEntity = restClientService.authenticatedExchange(baseUrl+"media/" + media.getId(), HttpMethod.PUT, media, Media.class);
        return responseEntity.getBody();
    }

    @Override
    public Media toggleVisibility(Media media) {
        ResponseEntity<Media> responseEntity = restClientService.authenticatedExchange(baseUrl+"media/"+media.getId()+"?toggleVisibility", HttpMethod.PUT, media, Media.class);
        return responseEntity.getBody();
    }

    @Override
    public Media removeMedia(Media media) {
        ResponseEntity<Media> responseEntity = restClientService.authenticatedExchange(baseUrl + "media/" + media.getId(), HttpMethod.DELETE, Media.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Media> sortByPopularity() {
        ResponseEntity<Media[]> responseEntity = template.getForEntity(baseUrl + "media/popular", Media[].class);
        return (List<Media>) Arrays.asList(responseEntity.getBody());
    }

}
