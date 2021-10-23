package com.jwt.token.project.jwt.token.example.controller;

import com.jwt.token.project.jwt.token.example.model.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.jwt.token.project.jwt.token.example.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jwt.token.project.jwt.token.example.service.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addroletouser")
    public ResponseEntity<?> saveUser(@RequestBody RoleToUser roleToUser) {
        userService.addRoleToUser(roleToUser.getUserName(), roleToUser.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/{roleName}")
    public ResponseEntity<Role>getRole(@PathVariable String roleName){
        return ResponseEntity.ok().body(userService.getRole(roleName));
    }
}

@Data
class RoleToUser {
    private String userName;
    private String roleName;
}