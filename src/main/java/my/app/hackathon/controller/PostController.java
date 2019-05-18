package my.app.hackathon.controller;

import com.alibaba.fastjson.JSONObject;
import my.app.hackathon.entity.Post;
import my.app.hackathon.entity.User;
import my.app.hackathon.repository.PostRepository;
import my.app.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/post/add")
    public Post addPost(Integer userId, Post post) {
        post.setSendTime(new Date());
        if (userId != null){
            Optional<User> user = userRepository.findById(userId);
            if (user != null){
                post.setUser(user.get());
            }
        }
        return postRepository.save(post);
    }

    @RequestMapping("/post/find")
    public Page<Post> findPosts(Integer page, Integer size) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size < 0) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findByOrderBySendTimeDesc(pageable);
        return posts;
    }

    @RequestMapping("/post/findAll")
    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }

    @RequestMapping("/post/find/{id}")
    public Optional<Post> findPost(@PathVariable("id") Integer id) {
        return postRepository.findById(id);
    }

    @RequestMapping("/post/update")
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @RequestMapping("/post/delete/{id}")
    public void delete(@PathVariable Integer id) {
        postRepository.deleteById(id);
    }
}
