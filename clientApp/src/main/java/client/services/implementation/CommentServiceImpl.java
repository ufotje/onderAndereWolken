package client.services.implementation;

import client.entities.Comment;
import client.entities.Story;
import client.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component("CommentService")
public class CommentServiceImpl implements CommentService {

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
    public Comment addComment(Comment comment) {
        ResponseEntity<Comment> responseEntity = restClientService.authenticatedExchange(baseUrl+"comment", HttpMethod.POST, comment, Comment.class);
        return responseEntity.getBody();
    }

    @Override
    public Comment deleteComment(Comment comment) {
        ResponseEntity<Comment> responseEntity = restClientService.authenticatedExchange(baseUrl + "comment/" + comment.getId(), HttpMethod.DELETE, Comment.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Comment> findAll() {
        ResponseEntity<Comment[]> responseEntity = template.getForEntity(baseUrl+"comment",Comment[].class);
        return (List<Comment>) Arrays.asList(responseEntity.getBody());
    }

    @Override
    public Comment findOne(int id) {
        return template.getForObject(baseUrl + "comment/{0}", Comment.class, id);
    }

    @Override
    public List<Comment> findAllCommentsByStory(Story story) {
        ResponseEntity<Comment[]> responseEntity = template.getForEntity(baseUrl+"comment/story/{0}",Comment[].class,story.getId());
        return (List<Comment>) Arrays.asList(responseEntity.getBody());
    }
}
