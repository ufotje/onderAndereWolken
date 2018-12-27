package be.intec;

import be.intec.entities.*;
import be.intec.repositories.CommentRepository;
import be.intec.repositories.StoryRepository;
import be.intec.repositories.TripRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class OnderAndereWolkenApp extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(OnderAndereWolkenApp.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OnderAndereWolkenApp.class, args);
        //instantiateSiteStatistics(context);

                TripRepository tripRepository= context.getBean("tripRepository", TripRepository.class);
//               List< Map<Trip, Integer>>trips = tripRepository.sortByPopularity();
//               for (Map<Trip,Integer>m:trips) {
//                   for (Map.Entry<Trip, Integer> entry : m.entrySet()) {
//                       System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
//                   }
//               }
//        List<TripLike>tripLikes = tripRepository.sortByPopularity();
//
//        for (TripLike tripLikes1: tripLikes){
//            System.out.println(tripLikes1.getTrip().getTitle()+ " "+tripLikes1.getTripLikes());
//        }

    }

 /*   private static void instantiateSiteStatistics(ConfigurableApplicationContext context) {
        TripRepository tripRepository = context.getBean("tripRepository", TripRepository.class);
        StoryRepository storyRepository = context.getBean("storyRepository", StoryRepository.class);
        CommentRepository commentRepository = context.getBean("commentRepository", CommentRepository.class);

        setAllTrips(tripRepository.count());
        setAllStories(storyRepository.count());
        setAllComments(commentRepository.count());

        Set<Country> visitedCountries = new HashSet<>();
        tripRepository.findAllTripsJoinCountries().forEach(trip ->
                trip.getCountries().forEach(country -> visitedCountries.add(country)));
        setAllVisitedCountries(visitedCountries.size());
    }*/


/*
    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://thor.intecbrussel.intra:3306/Pearl");
        driverManagerDataSource.setUsername("Pearl");
        driverManagerDataSource.setPassword("Pearl-123");
        return driverManagerDataSource;
    }*/
}
