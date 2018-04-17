package com.example.demo.controller;

import com.example.demo.domain.Group;
import com.example.demo.repository.GroupRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping()
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Long id) {
        return groupRepository.getOne(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGroupById(@PathVariable Long id) {
        groupRepository.deleteById(id);
    }

    @PutMapping()
    public Group editGroup(@RequestBody Group group) {
        return groupRepository.save(group);
    }
}
