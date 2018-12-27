package client.services.implementation;

import client.entities.Guestbook;
import client.services.GuestbookService;
import client.services.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component("GuestbookService")
public class GuestbookServiceImpl implements GuestbookService {

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
    public List<Guestbook> findAllGuestbookEntries() {
        ResponseEntity<Guestbook[]> responseEntity = restTemplate.getForEntity(baseUrl + "guestbook", Guestbook[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public Guestbook findById(int id) {
        return restTemplate.getForObject(baseUrl + "guestbook/{0}", Guestbook.class, id);
    }

    @Override
    public Guestbook addGuestbook(Guestbook guestbook) {
        ResponseEntity<Guestbook> responseEntity = restClientService.authenticatedExchange(baseUrl + "guestbook", HttpMethod.POST, guestbook, Guestbook.class);
        return responseEntity.getBody();
    }

    @Override
    public Guestbook deleteGuestbook(Guestbook guestbook) {
        ResponseEntity<Guestbook> responseEntity = restClientService.authenticatedExchange(baseUrl + "guestbook/" + guestbook.getId(), HttpMethod.DELETE, guestbook, Guestbook.class);
        return responseEntity.getBody();
    }

}
