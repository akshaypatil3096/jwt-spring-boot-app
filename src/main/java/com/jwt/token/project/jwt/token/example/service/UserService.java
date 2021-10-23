package com.jwt.token.project.jwt.token.example.service;

import com.jwt.token.project.jwt.token.example.model.Role;
import com.jwt.token.project.jwt.token.example.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String user, String role);
    User getUser(String user);
    List<User> getUsers();
    Role getRole(String role);
}
