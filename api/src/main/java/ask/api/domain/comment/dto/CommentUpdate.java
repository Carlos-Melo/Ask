package ask.api.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentUpdate(@NotNull
                            Long id,
                            @NotBlank
                            String description,
                            Boolean isSolved) {
}
