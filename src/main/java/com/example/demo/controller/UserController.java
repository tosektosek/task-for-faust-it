package com.example.demo.controller;

import com.example.demo.domain.Group;
import com.example.demo.domain.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public UserController(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping()
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.getOne(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        User user = userRepository.getOne(id);
        for (Group g : user.getGroups())
            groupRepository.save(groupRepository.getOne(g.getId()).removeUser(user));

        userRepository.deleteById(id);
    }

    @PutMapping()
    public User editUser(@RequestBody User user) {
        user.getGroups().parallelStream().forEach(group -> System.out.println(group.getName()));

        return userRepository.save(user);
    }
}
