package ask.api.comment;

import ask.api.post.Post;
import ask.api.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public record CommentDetail(Long id,
                            String description,
                            Boolean isSolved,
                            LocalDate criationDate,
                            User user,
                            @JsonIgnore
                            Post post) {

    public CommentDetail(Comment comment){
        this(comment.getId(), comment.getDescription(), comment.getIsSolved(), comment.getCriationDate(), comment.getUser(), comment.getPost());
    }

}
