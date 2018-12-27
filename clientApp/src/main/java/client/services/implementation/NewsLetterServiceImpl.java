package client.services.implementation;
import client.entities.NewsLetter;
import client.services.NewsLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("newsLetterService")
public class NewsLetterServiceImpl implements NewsLetterService {

    private String baseUrl;
    private RestTemplate template;
    @Autowired
    private RestClientServiceImpl restClientService;

    @Value("${resource.server.socket}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    @Autowired
    public void setTemplate(RestTemplate template) {
        this.template = template;
    }
    @Override
    public NewsLetter subscribe(NewsLetter newsLetter) {
        System.out.println(newsLetter);
        ResponseEntity<NewsLetter> responseEntity = restClientService.authenticatedExchange(baseUrl+"newsletter", HttpMethod.POST, newsLetter, NewsLetter.class);
        return responseEntity.getBody();
    }

    @Override
    public NewsLetter findByMail(String mail) {
        return template.getForObject(baseUrl + "newsletter/-{mail}-", NewsLetter.class, mail);
    }

    @Override
    public void deleteNewsletter(NewsLetter newsLetter) {
        System.out.println("deletenewsletter: " + newsLetter);
        int id = newsLetter.getId();

        try {

            ResponseEntity<NewsLetter> responseEntity = restClientService.authenticatedExchange(baseUrl + "newsletter/" + id, HttpMethod.DELETE, NewsLetter.class);


        } catch (HttpMessageNotReadableException e) {
            e.printStackTrace();
        }

    }
}