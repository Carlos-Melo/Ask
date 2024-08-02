package ask.api.service;

import ask.api.domain.post.Post;
import ask.api.domain.post.PostRepository;
import ask.api.domain.post.dto.PostCreate;
import ask.api.domain.post.dto.PostDetail;
import ask.api.domain.post.dto.PostList;
import ask.api.domain.post.dto.PostUpdate;
import ask.api.domain.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {


    PostRepository postRepository;
    UserRepository userRepository;


    public PostDetail savePost(PostCreate data) {

        if(!userRepository.existsById(data.user().getId())) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        var post = new Post(data);

        postRepository.save(post);

        return new PostDetail(post);
    }

    public Page<PostList> findAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(PostList::new);
    }

    public PostDetail findPostById(Long id) {
        var post = postRepository.findById(id);

        if(post.isEmpty()) {
            throw new RuntimeException("Post not found!");
        }

        return new PostDetail(post.get());
    }

    public PostDetail updatePost(PostUpdate postDto) {
        this.findPostById(postDto.id());
        var post = postRepository.getReferenceById(postDto.id());
        post.updatePost(postDto);

        return new PostDetail(post);
    }

    public void deletePost(Long id) {
        this.findPostById(id);
        postRepository.deleteById(id);
    }
}
