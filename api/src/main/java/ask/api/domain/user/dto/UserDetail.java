package ask.api.domain.user.dto;

import ask.api.domain.user.Users;

public record UserDetail(Long id, String name, String email, String password, Boolean isActive, byte[] image) {

    public UserDetail(Users user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getIsActive(), user.getImage());
    }
}
