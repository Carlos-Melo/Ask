package ask.api.domain.user.dto;

import ask.api.domain.user.UserType;
import jakarta.validation.constraints.NotNull;

public record UserUpdate(
        @NotNull
        Long id,
        String name,
        String email,
        String password,
        Boolean isActive,
        String image,
        UserType userType) {
}
