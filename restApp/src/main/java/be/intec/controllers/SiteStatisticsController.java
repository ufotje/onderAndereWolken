package be.intec.controllers;

import be.intec.bean.SiteStatistics;
import be.intec.services.ManageSiteStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics")
public class SiteStatisticsController {

    @Autowired
    private ManageSiteStatistics manageSiteStatistics;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SiteStatistics getSiteStatistics() {
        SiteStatistics statistics = new SiteStatistics();
        statistics.setAllTrips(manageSiteStatistics.countTrips());
        statistics.setAllVisitedCountries(manageSiteStatistics.countVisitedCountries());
        statistics.setAllPictures(manageSiteStatistics.countPictures());
        statistics.setAllStories(manageSiteStatistics.countStories());
        statistics.setAllMovies(manageSiteStatistics.countMovies());
        statistics.setAllComments(manageSiteStatistics.countComments());
        return statistics;
    }
}
