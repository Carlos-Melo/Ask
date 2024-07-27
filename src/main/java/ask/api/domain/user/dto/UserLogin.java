package ask.api.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLogin(
    @NotBlank
    @Email
    String email,
    @NotBlank
    String password){
}
