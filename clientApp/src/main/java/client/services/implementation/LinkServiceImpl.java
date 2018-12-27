package client.services.implementation;

import client.entities.Link;
import client.services.LinkService;
import client.services.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component("LinkService")
public class LinkServiceImpl implements LinkService {

    private String baseUrl;
    private RestTemplate restTemplate;

    @Autowired
    private RestClientService restClientService;

    @Value("${resource.server.socket}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Link> findAllLinks() {
        ResponseEntity<Link[]> responseEntity = restTemplate.getForEntity(baseUrl + "links", Link[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public Link findById(int id) {
        return restTemplate.getForObject(baseUrl + "links/{0}", Link.class, id);
    }

    @Override
    public Link findByUrl(String linkUrl) {
        return restTemplate.getForObject(baseUrl + "links/url/{0}", Link.class, linkUrl);
    }

    @Override
    public Link addLink(Link link) {
        ResponseEntity<Link> responseEntity = restClientService.authenticatedExchange(baseUrl + "links", HttpMethod.POST, link, Link.class);
        return responseEntity.getBody();
    }

    @Override
    public Link updateLink(Link link) {
        ResponseEntity<Link> responseEntity = restClientService.authenticatedExchange(baseUrl + "links/" + link.getId(), HttpMethod.PUT, link, Link.class);
        return responseEntity.getBody();
    }

    @Override
    public Link deleteLink(Link link) {
        ResponseEntity<Link> responseEntity = restClientService.authenticatedExchange(baseUrl + "links/" + link.getId(), HttpMethod.DELETE, link, Link.class);
        return responseEntity.getBody();
    }

    @Override
    public String checkUrl(String linkUrl) {
        if (!linkUrl.startsWith("http://") || !linkUrl.startsWith("https://")) {
            return "http://".concat(linkUrl);
        } else {
            return linkUrl;
        }
    }
}
