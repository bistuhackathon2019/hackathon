package my.app.hackathon.controller;

import my.app.hackathon.entity.Comment;
import my.app.hackathon.entity.Post;
import my.app.hackathon.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/comment")
    public List<Comment> findCommentsByPostId(Integer postId){
        Post post = new Post();
        post.setPostId(postId);
        return commentRepository.findByPost(post);
    }
}
