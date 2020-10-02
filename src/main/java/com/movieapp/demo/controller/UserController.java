package com.movieapp.demo.controller;

import com.movieapp.demo.models.LoginModel;
import com.movieapp.demo.models.UserEntity;
import com.movieapp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin(origins = "http://localhost:4202" )
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getUsers")
    public Object getUsers(){
        try{
            List<UserEntity> users = this.userRepository.findAll();

            return users;
        } catch (Exception exception){
            return exception;
        }
    }
    @PostMapping("/register")
    public Object insertUser(@RequestBody UserEntity userEntity )
    {
        try {
            String password = passwordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(password);
            return this.userRepository.save(userEntity);
        } catch(Exception exception)
        {
            return exception;
        }
    }
}