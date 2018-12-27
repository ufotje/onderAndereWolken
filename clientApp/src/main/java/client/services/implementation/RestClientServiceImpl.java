package client.services.implementation;

import client.services.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component("RestClientService")
public class RestClientServiceImpl<T> implements RestClientService<T> {

    private String baseURL;

    @Autowired
    private RestTemplate template;

    @Value("${resource.server.socket}")
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    @Value("${security.user.name}")
    private String username;

    @Value("${security.user.password}")
    private String password;

    @Override
    public ResponseEntity<T> authenticatedExchange(String url, HttpMethod method, Class<T> responseType) {
        HttpHeaders headers = createAuthenticationHeader();
        return template.exchange(url, method, new HttpEntity<String>(headers), responseType);
    }

    @Override
    public ResponseEntity<T> authenticatedExchange(String url, HttpMethod method, T entityBody, Class<T> responseType) {
        HttpHeaders headers = createAuthenticationHeader();
        return template.exchange(url, method, new HttpEntity<>(entityBody, headers), responseType);
    }

    @Override
    public HttpHeaders createAuthenticationHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", createAuthenticationValue());
        List<MediaType> accepted = new ArrayList<>();
        accepted.add(MediaType.APPLICATION_JSON);
        headers.setAccept(accepted);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    public String createAuthenticationValue(){
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(Charset.forName("UTF-8")));
        String authHeader = "Basic " + encodedAuth;
        return authHeader;
    }

}
