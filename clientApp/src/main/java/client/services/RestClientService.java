package client.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface RestClientService<T> {

    ResponseEntity<T> authenticatedExchange(String url, HttpMethod method, Class<T> responseType);

    ResponseEntity<T> authenticatedExchange(String url, HttpMethod method, T entityBody, Class<T> responseType);

    HttpHeaders createAuthenticationHeader();

    String createAuthenticationValue();
}
