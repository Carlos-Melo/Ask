package ask.api.controller;

import ask.api.user.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid UserCreate data, UriComponentsBuilder uriBuilder) {
        var user = new User(data);
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
        var user = repository.getReferenceById(id);

        return ResponseEntity.ok(new UserDetail(user));
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
