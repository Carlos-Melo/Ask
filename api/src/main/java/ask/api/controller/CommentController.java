package ask.api.controller;

import ask.api.comment.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid CommentCreate comment) {
        repository.save(new Comment(comment));
    }

    @GetMapping
    public Page<CommentList> list(Pageable pageable) {
       return repository.findAll(pageable).map(CommentList::new);
    }

    @GetMapping("/id")
    public Optional<Comment> getComment(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid CommentUpdate data) {
        var comment = repository.getReferenceById(data.id());
        comment.updateComment(data);
    }

    @DeleteMapping("/id")
    @Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

}
