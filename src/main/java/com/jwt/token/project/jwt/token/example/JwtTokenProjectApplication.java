package com.jwt.token.project.jwt.token.example;

import com.jwt.token.project.jwt.token.example.model.Role;
import com.jwt.token.project.jwt.token.example.model.User;
import com.jwt.token.project.jwt.token.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtTokenProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(JwtTokenProjectApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));

			userService.saveUser(new User(null, "Akshay","akshay","123",new ArrayList<>()));
			userService.saveUser(new User(null, "Pooja","pooja","321",new ArrayList<>()));
			userService.saveUser(new User(null, "Ashok","ashok","1234",new ArrayList<>()));

			userService.addRoleToUser("akshay","ROLE_MANAGER");
			userService.addRoleToUser("akshay","ROLE_ADMIN");
			userService.addRoleToUser("pooja","ROLE_MANAGER");
			userService.addRoleToUser("ashok","ROLE_USER");
		};
	}
}
