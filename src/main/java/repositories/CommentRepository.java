package repositories;

import data.Comment;
import data.CommentID;
import exceptions.CommentNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommentRepository {
  private final CommentID commentID = new CommentID(0);
  Map<CommentID, Comment> comments = new ConcurrentHashMap<>();

  public CommentID generateID() {
    return commentID.generate();
  }

  public Comment getComment(CommentID id) {
    Comment comment = comments.get(id);
    if (comment == null) {
      throw new CommentNotFoundException("Comment with id=" + id + " wasnt found!");
    }
    return comment;
  }

  public CommentID addComment(Comment comment) {
    comments.put(commentID, comment);
    return commentID;
  }

  public void deleteComment(CommentID id) {
    Comment comment = comments.get(id);
    if (comment == null) {
      throw new CommentNotFoundException("Comment with id=" + id + " wasnt found!");
    }
    comments.remove(id);
  }
}
