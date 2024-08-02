package ask.api.controller;

import ask.api.domain.user.*;
import ask.api.domain.user.dto.*;
import ask.api.service.UserService;
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
    UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid UserCreate data, UriComponentsBuilder uriBuilder) {

        var user = userService.saveUser(data);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public ResponseEntity<Page<UserListAll>> list(@PageableDefault(sort = {"criationDate"}) Pageable pagination) {
        var page = userService.findAllUsers(pagination);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetail> returnOne(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);

        return ResponseEntity.ok(new UserDetail(user.get()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UserUpdate data) {
        var user = userService.findUserById(data.id());

        user.get().updateUser(data);

        return ResponseEntity.ok(new UserDetail(user.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
