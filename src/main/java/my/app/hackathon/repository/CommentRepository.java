package my.app.hackathon.repository;

import my.app.hackathon.entity.Comment;
import my.app.hackathon.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer>{
    List<Comment> findByPostOrderByCommentTimeDesc(Post post);
}
