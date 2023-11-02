package ask.api.post;

import jakarta.validation.constraints.NotNull;

public record PostUpdate(
         @NotNull
         Long id,
         String title,
         String description,
         Status status) {
}
