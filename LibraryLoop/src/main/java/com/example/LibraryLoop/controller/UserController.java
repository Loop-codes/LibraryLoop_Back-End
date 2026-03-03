package com.example.LibraryLoop.controller;

import com.example.LibraryLoop.Repository.userRepository;
import com.example.LibraryLoop.entity.User;
import com.example.LibraryLoop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private userRepository userRepository;

    @GetMapping("/listall")
    public List<User> listAll(){ return userService.findAll(); }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/getuser/{id}")
    public Optional<User> getUserByID(@PathVariable Long id){
        return userService.findById(id);
    }

    @PutMapping("/updateuser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(user,id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
