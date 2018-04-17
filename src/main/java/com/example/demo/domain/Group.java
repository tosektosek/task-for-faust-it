package com.example.demo.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @JsonIgnoreProperties("groups")
    @ManyToMany
    @JoinTable(name = "USERS_GROUPS",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Group() {
    }

    public Group(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public Group removeUser(User user) {
        this.users.remove(user);
        return this;
    }
}
