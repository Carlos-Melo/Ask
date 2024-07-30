package ask.api.controller;

import ask.api.domain.user.*;
import ask.api.domain.user.dto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid UserCreate data, UriComponentsBuilder uriBuilder) {

        if(repository.existsByEmail(data.email())) {
            throw new RuntimeException("Usuário já cadastrado!");
        }

        String encodedPassword = passwordEncoder.encode(data.password());

        UserCreate newUser = new UserCreate(data.name(), data.email(), encodedPassword);

        var user = new Users(newUser);

        repository.save(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public ResponseEntity<Page<UserListAll>> list(@PageableDefault(sort = {"criationDate"}) Pageable pagination) {
        var page = repository.findAll(pagination).map(UserListAll::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetail> returnOne(@PathVariable Long id) {
        Optional<Users> user = repository.findById(id);

        if(user.isEmpty() || !user.get().getIsActive()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new UserDetail(user.get()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UserUpdate data) {
        var user = repository.getReferenceById(data.id());
        user.updateUser(data);

        return ResponseEntity.ok(new UserDetail(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
