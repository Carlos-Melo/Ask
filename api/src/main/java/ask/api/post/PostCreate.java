package ask.api.post;

import ask.api.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record PostCreate(
    @NotBlank
    String title,
    @NotBlank
    String description,
    @Valid
    Status status,
    @Valid
    User user) {
}
