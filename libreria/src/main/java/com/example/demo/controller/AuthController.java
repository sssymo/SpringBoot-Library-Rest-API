package com.example.demo.controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.security.AuthRequest;
import com.example.demo.security.JwtUtils;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserDetailsImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.config.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private PasswordEncoder passwordEncoder; 
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
    public String authenticateUser(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtUtils.generateToken(userDetails);
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody AuthRequest authRequest) {
        
        if (userRepository.findByUsername(authRequest.getUsername()).isPresent()) {
            return "user already exists"; //non lo registro
        }

        
        User newUser = new User();
        newUser.setUsername(authRequest.getUsername());
  
        String encodedPassword = passwordEncoder.encode(authRequest.getPassword());
        newUser.setPassword(encodedPassword);
        Set<String> roles = new HashSet<>();
        roles.add("USER"); //di default setto user
        newUser.setRoles(roles);
        newUser.setEnabled(true);

        
        userRepository.save(newUser);

        return "user registered successfully";
    }
}
