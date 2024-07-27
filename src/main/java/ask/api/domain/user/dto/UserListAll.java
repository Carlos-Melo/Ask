package ask.api.domain.user.dto;
import ask.api.domain.user.Users;
import ask.api.domain.user.UserType;

import java.time.LocalDate;

public record UserListAll(Long id, String name, String email, Boolean isActive, byte[] image, UserType userType, LocalDate criationDate) {

    public UserListAll(Users user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getIsActive(), user.getImage(), user.getUserType(), user.getCriationDate());
    }

}
