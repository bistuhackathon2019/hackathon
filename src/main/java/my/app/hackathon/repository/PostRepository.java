package my.app.hackathon.repository;

import my.app.hackathon.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Integer>{
    Page<Post> findByOrderBySendTimeDesc(Pageable pageable);
}
