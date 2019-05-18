package my.app.hackathon.controller;

import my.app.hackathon.entity.Post;
import my.app.hackathon.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    PostRepository postRepository;

    @PostMapping("/post")
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/post")
    public Page<Post> findPosts(Integer page, Integer size) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size < 0) {
            size = 10;
        }
        PageRequest pageable= PageRequest.of(page,size);
        return postRepository.findAll(pageable);
    }

    @GetMapping("/post/{id}")
    public Optional<Post> findPost(@PathVariable("id") Integer id) {
        return postRepository.findById(id);
    }

    @PostMapping("/post/update")
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @DeleteMapping("/post/{id}")
    public void delete(@PathVariable Integer id) {
        postRepository.deleteById(id);
    }
}
