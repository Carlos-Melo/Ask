package ask.api.post;

import ask.api.comment.Comment;
import ask.api.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "post")
@Entity(name = "post")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate criationDate = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comment = new ArrayList<>();

    public Post(PostCreate post) {
        this.title = post.title();
        this.description = post.description();
        this.status = post.status();
        this.user = post.user();
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
