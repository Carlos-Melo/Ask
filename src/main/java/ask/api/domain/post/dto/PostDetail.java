package ask.api.domain.post.dto;

import ask.api.domain.comment.Comment;
import ask.api.domain.post.Post;
import ask.api.domain.post.Status;
import ask.api.domain.user.Users;

import java.time.LocalDate;
import java.util.List;

public record PostDetail(Long id, String title, String description, LocalDate criationDate, Status status, Users user, List<Comment> comment) {

    public PostDetail(Post post) {
        this(post.getId(), post.getTitle(), post.getDescription(), post.getCriationDate(), post.getStatus(), post.getUser(), post.getComment());
    }

}
