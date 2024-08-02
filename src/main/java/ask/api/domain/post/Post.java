package ask.api.domain.post;

import ask.api.domain.comment.Comment;
import ask.api.domain.post.dto.PostCreate;
import ask.api.domain.post.dto.PostUpdate;
import ask.api.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate criationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comment;

    public Post(PostCreate post) {
        this.title = post.title();
        this.description = post.description();
        this.status = Status.P;
        this.user = post.user();
        this.criationDate = LocalDate.now();
        this.comment = new ArrayList<>();
    }

    public void updatePost(PostUpdate postUpdate) {
        if(postUpdate.title() != null) {
            this.title = postUpdate.title();
        }

        if(postUpdate.description() != null) {
            this.description = postUpdate.description();
        }

        if(postUpdate.status() != null) {
            this.status = postUpdate.status();
        }

    }

}
