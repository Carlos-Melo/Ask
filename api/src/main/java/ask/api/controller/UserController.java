package ask.api.controller;

import ask.api.user.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid UserCreate data) {
        repository.save(new User(data));
    }

    @GetMapping
    public Page<UserListAll> list(@PageableDefault(sort = {"criationDate"}) Pageable pagination) {
        return repository.findAll(pagination).map(UserListAll::new);
    }

    @GetMapping("/{id}")
    public Optional<User> returnOne(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UserUpdate data) {
        var user = repository.getReferenceById(data.id());
        user.updateUser(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
