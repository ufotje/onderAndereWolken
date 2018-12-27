package client.services;

import client.entities.Comment;
import client.entities.Story;

import java.util.List;

/**
 * Created by PearlS on 6/11/2017.
 */
public interface CommentService {
    Comment addComment(Comment comment);
    Comment deleteComment(Comment comment);
    List<Comment> findAll();
    Comment findOne(int id);
    List<Comment> findAllCommentsByStory(Story story);
}
