package my.app.hackathon.repository;

import my.app.hackathon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
    User findByPhone(String phone);
}
