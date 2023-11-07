package ask.api.controller;

import ask.api.comment.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CommentCreate data, UriComponentsBuilder uriBuilder) {
        var comment = repository.save(new Comment(data));

        var uri = uriBuilder.path("comment/{id}").buildAndExpand(comment.getId()).toUri();

        return ResponseEntity.created(uri).body(comment);
    }

    @GetMapping
    public ResponseEntity<Page<CommentList>> list(Pageable pageable) {
       var comment = repository.findAll(pageable).map(CommentList::new);

       return ResponseEntity.ok(comment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDetail> getComment(@PathVariable Long id) {
        var comment = repository.getReferenceById(id);

        return ResponseEntity.ok(new CommentDetail(comment));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CommentDetail> update(@RequestBody @Valid CommentUpdate data) {
        var comment = repository.getReferenceById(data.id());
        comment.updateComment(data);

        return ResponseEntity.ok(new CommentDetail(comment));
    }

    @DeleteMapping("/id")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
