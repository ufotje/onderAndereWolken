package be.intec.repositories;

import be.intec.entities.Story;
import be.intec.entities.Tag;
import be.intec.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by PearlS on 13/10/2017.
 */
public interface TagRepository extends JpaRepository<Tag,Integer>, JpaSpecificationExecutor<Tag> {

    @Transactional
    Tag findTagByWord(String word);


}
