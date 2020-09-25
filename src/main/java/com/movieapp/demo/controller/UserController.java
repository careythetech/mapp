package com.movieapp.demo.controller;


import com.movieapp.demo.models.UserEntity;
import com.movieapp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getUsers")
    public Object getUsers(){
        try{
            return this.userRepository.findAll();
        } catch (Exception exception){
            return exception;
        }
    }
    @PostMapping("/register")
    public Object insertUser(@RequestBody UserEntity userEntity )
    {
        try {
            return userRepository.save(userEntity);
        } catch(Exception exception)
        {
            return exception;
        }
    }

}
