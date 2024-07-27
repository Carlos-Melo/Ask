package ask.api.controller;

import ask.api.domain.post.*;
import ask.api.domain.post.dto.PostCreate;
import ask.api.domain.post.dto.PostDetail;
import ask.api.domain.post.dto.PostList;
import ask.api.domain.post.dto.PostUpdate;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/post")
@SecurityRequirement(name = "bearer-key")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PostCreate data, UriComponentsBuilder uriBulder) {
        var post = new Post(data);
        postRepository.save(post);

        var uri = uriBulder.path("/post/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).body(post);
    }

    @GetMapping
    public ResponseEntity<Page<PostList>> list(@PageableDefault(sort = "criationDate") Pageable pageable) {
        var post = postRepository.findAll(pageable).map(PostList::new);

        return ResponseEntity.ok(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetail> getPost(@PathVariable Long id) {
        var post = postRepository.getReferenceById(id);

        return ResponseEntity.ok(new PostDetail(post));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PostDetail> update(@RequestBody @Valid PostUpdate data) {
        var post = postRepository.getReferenceById(data.id());
        post.updatePost(data);

        return ResponseEntity.ok(new PostDetail(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        postRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
