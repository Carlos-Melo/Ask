package ask.api.service;

import ask.api.domain.user.User;
import ask.api.domain.user.UserRepository;
import ask.api.domain.user.dto.UserCreate;
import ask.api.domain.user.dto.UserListAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserCreate userDto) {
        if(userRepository.existsByEmail(userDto.email())) {
            throw new RuntimeException("Usuário já cadastrado!");
        }

        String encodedPassword = passwordEncoder.encode(userDto.password());

        UserCreate newUser = new UserCreate(userDto.name(), userDto.email(), encodedPassword);

        var user = new User(newUser);

        userRepository.save(user);

        return user;
    }

    public Page<UserListAll> findAllUsers(Pageable pagination) {
        return userRepository.findAll(pagination).map(UserListAll::new);
    }

    public Optional<User> findUserById(Long id) {
        var user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        if(!user.get().getIsActive()) {
            throw new RuntimeException("User is not active!");
        }

        return user;
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        userRepository.deleteById(id);
    }
}
