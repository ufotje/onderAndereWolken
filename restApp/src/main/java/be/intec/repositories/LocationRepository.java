package be.intec.repositories;

import be.intec.entities.Location;
import be.intec.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Transactional
    List<Location> findAllByTrip(Trip trip);

}
