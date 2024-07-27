package ask.api.domain.post.dto;

import ask.api.domain.post.Status;
import jakarta.validation.constraints.NotNull;

public record PostUpdate(
         @NotNull
         Long id,
         String title,
         String description,
         Status status) {
}
