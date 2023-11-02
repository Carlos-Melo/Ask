package ask.api.comment;

import ask.api.post.Post;
import ask.api.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "comment")
@Entity(name = "comment")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean isSolved;
    private final LocalDate criationDate = LocalDate.now();
    @ManyToOne
    private User user;
    @ManyToOne
    @JoinColumn(name = "id")
    private Post post;

    public Comment(CommentCreate comment) {
        this.description = comment.description();
        this.isSolved = false;
        this.user = comment.user();
        this.post = comment.post();
    }

    public void updateComment(CommentUpdate comment) {
        if(comment.description() != null) {
            this.description = comment.description();
        }

        if(comment.isSolved() != null) {
            this.isSolved = comment.isSolved();
        }
    }

}
