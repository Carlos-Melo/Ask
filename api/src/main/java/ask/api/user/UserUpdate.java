package ask.api.user;

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
