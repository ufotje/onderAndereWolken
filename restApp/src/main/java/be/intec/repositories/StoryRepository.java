package be.intec.repositories;

import be.intec.entities.Story;
import be.intec.entities.Tag;
import be.intec.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by DanielD on 6/10/2017.
 */
public interface StoryRepository extends JpaRepository<Story,Integer>, JpaSpecificationExecutor<Story> {

    @Transactional
    Story findStoryById(int id);

    @Transactional
    Story findByTitle(String title);

    @Transactional
    List<Story> findStoryByTrip(Trip trip);

    @Transactional
    List<Story> findAllByVisibleAndDeletedOrderByLikesDesc(boolean isVisible, boolean isDeleted);

    @Transactional
    @Query("SELECT s FROM Story s INNER join s.tags t where t = ?1 and s.deleted = false and s.visible = true")
    List<Story> findStoriesByTag(Tag tag);
}
