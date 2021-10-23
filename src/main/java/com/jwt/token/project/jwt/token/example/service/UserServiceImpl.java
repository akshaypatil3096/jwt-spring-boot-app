package com.jwt.token.project.jwt.token.example.service;


import com.jwt.token.project.jwt.token.example.model.Role;
import com.jwt.token.project.jwt.token.example.model.User;
import com.jwt.token.project.jwt.token.example.repository.RoleRepository;
import com.jwt.token.project.jwt.token.example.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user==null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("Username not found in the database");
        }else{
            log.info("User found in database: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to db",user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to db",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user= userRepository.findByUserName(userName);
        Role role= roleRepository.findByName(roleName);
        log.info("adding role {} to user {}",roleName,userName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String user) {
        log.info("Getting user from userName {}",user);
        return userRepository.findByUserName(user);
    }

    @Override
    public List<User> getUsers() {
        log.info("Getting all users from db");
        return userRepository.findAll();
    }

    @Override
    public Role getRole(String role) {
        return roleRepository.findByName(role);
    }
}
