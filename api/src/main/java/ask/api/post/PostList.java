package ask.api.post;

import ask.api.comment.Comment;
import ask.api.user.User;
import java.time.LocalDate;
import java.util.List;

public record PostList(Long id, String title, String description, LocalDate criationDate, Status status, User user, List<Comment> comment) {
    public PostList(Post post) {
        this(post.getId(), post.getTitle(), post.getDescription(), post.getCriationDate(), post.getStatus(), post.getUser(), post.getComment());
    }
}
