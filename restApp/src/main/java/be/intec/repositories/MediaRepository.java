package be.intec.repositories;

import be.intec.entities.Media;
import be.intec.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MediaRepository extends JpaRepository<Media,Integer>, JpaSpecificationExecutor<Media> {

    @Transactional
    Media findMediaByTitle(String title);

    @Transactional
    List<Media> findMediaByTrip(Trip trip);

    @Transactional
    List<Media> findAllByVisibleAndDeletedOrderByLikesDesc(boolean isVisible, boolean isDeleted);
}
