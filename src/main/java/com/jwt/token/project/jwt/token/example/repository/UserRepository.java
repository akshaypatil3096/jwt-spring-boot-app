package com.jwt.token.project.jwt.token.example.repository;


import com.jwt.token.project.jwt.token.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
