package be.intec.repositories;

import be.intec.entities.Comment;
import be.intec.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by PearlS on 6/11/2017.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Transactional
    public List<Comment> findCommentByStory(Story story);
}
