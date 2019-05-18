package my.app.hackathon.controller;

import my.app.hackathon.entity.User;
import my.app.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    //添加用户
    @PostMapping("/user")
    public User insertUser(User user){
        return userRepository.save(user);
    }

    //查询用户
    @GetMapping("/user/{id}")
    public Optional<User> findUser(@PathVariable("id") Integer id){
        return userRepository.findById(id);
    }

    //查询所有用户
    @GetMapping("/user")
    public List<User> findUser(){
        return userRepository.findAll();
    }

    //更改用户
    @PostMapping("/user/update")
    public User updateUser(User user){
        return userRepository.save(user);
    }

    //删除用户
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
    }
}
