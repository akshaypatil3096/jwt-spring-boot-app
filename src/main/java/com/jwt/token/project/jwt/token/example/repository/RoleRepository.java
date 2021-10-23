package com.jwt.token.project.jwt.token.example.repository;


import com.jwt.token.project.jwt.token.example.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
