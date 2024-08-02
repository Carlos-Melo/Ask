package ask.api.domain.comment;

import ask.api.domain.comment.dto.CommentCreate;
import ask.api.domain.comment.dto.CommentUpdate;
import ask.api.domain.post.Post;
import ask.api.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Boolean isSolved;

    private LocalDate criationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Comment(CommentCreate comment) {
        this.description = comment.description();
        this.isSolved = false;
        this.user = comment.user();
        this.post = comment.post();
        this.criationDate = LocalDate.now();
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
