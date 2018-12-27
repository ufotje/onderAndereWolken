package client.services.implementation;

import client.entities.Contact;
import client.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by PearlS on 13/11/2017.
 */
@Service("ContactService")
public class ContactServiceImpl implements ContactService {

    private String baseUrl;
    private RestTemplate template;
    @Autowired
    private RestClientServiceImpl restClientService;

    @Value("${resource.server.socket}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Autowired
    public void setRestTemplate(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<Contact> getAllContacts() {
        ResponseEntity<Contact[]> response = template.getForEntity(baseUrl + "contacts", Contact[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public void deleteContact(Contact contact) {
        try{
        ResponseEntity<Contact> responseEntity = restClientService.authenticatedExchange(baseUrl + "contacts/" + contact.getId(), HttpMethod.DELETE, Contact.class);
    } catch (HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
    }

    @Override
    public Contact addContact(Contact contact) {
        ResponseEntity<Contact> responseEntity = restClientService.authenticatedExchange(baseUrl+"contacts", HttpMethod.POST, contact, Contact.class);
        return responseEntity.getBody();
    }

    @Override
    public Contact getContactById(int id) {
        return template.getForObject(baseUrl + "contacts/{0}", Contact.class, id);
    }
}
