package ask.api.comment;

import ask.api.post.Post;
import ask.api.user.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CommentCreate(
        @NotBlank
        String description,
        @Valid
        User user,
        @Valid
        Post post) {
}
