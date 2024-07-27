package ask.api.domain.comment.dto;

import ask.api.domain.comment.Comment;
import ask.api.domain.post.Post;
import ask.api.domain.user.Users;

import java.time.LocalDate;

public record CommentList( Long id, String description, Boolean isSolved, LocalDate criationDate, Users user, Post post) {
    public CommentList(Comment comment) {
        this(comment.getId(), comment.getDescription(), comment.getIsSolved(), comment.getCriationDate(), comment.getUser(), comment.getPost());
    }
}
