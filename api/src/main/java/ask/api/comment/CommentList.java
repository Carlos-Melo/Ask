package ask.api.comment;

import ask.api.post.Post;
import ask.api.user.User;

import java.time.LocalDate;

public record CommentList( Long id, String description, Boolean isSolved, LocalDate criationDate, User user, Post post) {
    public CommentList(Comment comment) {
        this(comment.getId(), comment.getDescription(), comment.getIsSolved(), comment.getCriationDate(), comment.getUser(), comment.getPost());
    }
}
