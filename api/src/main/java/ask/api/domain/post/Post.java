package ask.api.domain.post;

import ask.api.domain.comment.Comment;
import ask.api.domain.post.dto.PostCreate;
import ask.api.domain.post.dto.PostUpdate;
import ask.api.domain.user.Users;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Users user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
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
