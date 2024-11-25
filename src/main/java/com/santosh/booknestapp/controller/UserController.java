package com.santosh.booknestapp.controller;

import com.santosh.booknestapp.model.User;
import com.santosh.booknestapp.service.UserService;
import com.santosh.booknestapp.util.AuthModel;
import com.santosh.booknestapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        String userCreation = userService.register(user);
        if (!"success".equals(userCreation)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "User registration failed"));
        }

        AuthModel authModel = new AuthModel();
        authModel.setAccessToken(jwtUtil.generateToken(user.getEmail()));
        authModel.setUserId(user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(authModel);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Authentication failed for Login"));
        }

        user=userService.findUserByEmail(user.getEmail());

        AuthModel authModel = new AuthModel();
        authModel.setAccessToken(jwtUtil.generateToken(user.getEmail()));
        authModel.setUserId(user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(authModel);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        User user=userService.findUserById(id);
        return ResponseEntity.ok(user);
    }
}
