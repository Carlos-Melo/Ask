package ask.api.domain.comment.dto;

import ask.api.domain.comment.Comment;
import ask.api.domain.post.Post;
import ask.api.domain.user.User;
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
