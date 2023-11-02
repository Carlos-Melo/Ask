package ask.api.controller;

import ask.api.post.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    PostRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid PostCreate post) {
        repository.save(new Post(post));
    }

    @GetMapping
    public Page<PostList> list(@PageableDefault(sort = "criationDate") Pageable pageable) {
        return repository.findAll(pageable).map(PostList::new);
    }

    @GetMapping("/id")
    public Optional<Post> getPost(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PostUpdate data) {
        var post = repository.getReferenceById(data.id());
        post.updatePost(data);
    }

    @DeleteMapping("/id")
    @Transactional
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
