package my.app.hackathon.controller;

import my.app.hackathon.entity.Comment;
import my.app.hackathon.entity.Post;
import my.app.hackathon.repository.CommentRepository;
import my.app.hackathon.repository.PostRepository;
import my.app.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/comment/findByPostId/{postId}")
    public List<Comment> findCommentsByPostId(@PathVariable("postId") Integer postId) {
        Post post = new Post();
        post.setPostId(postId);
        return commentRepository.findByPostOrderByCommentTimeDesc(post);
    }

    @RequestMapping("/comment/findById/{id}")
    public Optional<Comment> findCommentById(@PathVariable("id") Integer id) {
        return commentRepository.findById(id);
    }

    @RequestMapping("/comment/add")
    public Comment addComment(Integer postId, Integer userId, Comment comment) {
        comment.setCommentTime(new Date());
        if (postId != null) {
            comment.setPost(postRepository.findById(postId).get());
        }
        if (userId != null) {
            comment.setUser(userRepository.findById(userId).get());
        }
        return commentRepository.save(comment);
    }

    @RequestMapping("/comment/update")
    public Comment updateComment(Integer postId, Integer userId, Comment comment) {
        comment.setCommentTime(new Date());
        if (postId != null) {
            comment.setPost(postRepository.findById(postId).get());
        }
        if (userId != null) {
            comment.setUser(userRepository.findById(userId).get());
        }
        return commentRepository.save(comment);
    }

    @RequestMapping("/comment/delete/{id}")
    public void deleteComment(@PathVariable("id") Integer id) {
        commentRepository.deleteById(id);
    }
}
