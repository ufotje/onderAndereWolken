package be.intec.repositories;

import be.intec.entities.Country;
import be.intec.entities.Trip;
import be.intec.entities.TripLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip,Integer>, JpaSpecificationExecutor<Trip>{

    @Transactional
    List<Trip> findAllByOrderByLatestEditDesc();

    @Transactional
    List<Trip> getTripsByDeletedIsFalse();

    @Transactional
    List<Trip> findAllTripsByCountries(List<Country> countries);



    @Transactional
    @Query("SELECT new be.intec.entities.TripLike(t, SUM(s.likes)/COUNT(s)) FROM Trip t INNER join t.stories s WHERE t.visible = true AND t.deleted =false GROUP BY t ORDER BY SUM(s.likes)/COUNT(s)Desc")
    List<TripLike> sortByPopularity();

    @Transactional
    @Query("select t from Trip t join fetch t.countries c")
    List<Trip> findAllTripsJoinCountries();

    @Transactional
    Trip findByTitle(String title);

}


