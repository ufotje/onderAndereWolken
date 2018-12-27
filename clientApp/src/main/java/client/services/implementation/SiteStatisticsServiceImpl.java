package client.services.implementation;

import client.entities.SiteStatistics;
import client.services.SiteStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("SiteStatisticsService")
public class SiteStatisticsServiceImpl implements SiteStatisticsService{
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

    @Override
    public SiteStatistics getAllStatistics() {
        ResponseEntity<SiteStatistics> response = template.getForEntity(baseUrl + "/statistics", SiteStatistics.class);
        return response.getBody();
    }

}
