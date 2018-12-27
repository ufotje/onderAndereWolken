package client.services.implementation;

import client.entities.Picture;
import client.entities.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PictureServiceImpl {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resource.server.socket}")
    private String baseUrl;

    public Picture getPictureById(int id){
        return restTemplate.getForObject(baseUrl+"pictures/"+id, Picture.class);
    }

    public List<Picture> findAll() {
        List<Picture> pictures;
        ResponseEntity<Picture[]> response = restTemplate.getForEntity(baseUrl + "pictures", Picture[].class);
        pictures = Arrays.asList(response.getBody());
        return pictures;
    }

    public Picture getLatestPicture(){
        return restTemplate.getForObject(baseUrl+"pictures/latest", Picture.class);
    }
}
