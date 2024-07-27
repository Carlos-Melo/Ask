package ask.api.domain.comment.dto;

import ask.api.domain.post.Post;
import ask.api.domain.user.Users;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CommentCreate(
        @NotBlank
        String description,
        @Valid
        Users user,
        @Valid
        Post post) {
}
