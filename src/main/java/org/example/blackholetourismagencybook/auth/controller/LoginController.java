package org.example.blackholetourismagencybook.auth.controller;

import jakarta.validation.Valid;
import org.example.blackholetourismagencybook.auth.dto.*;
import org.example.blackholetourismagencybook.auth.entity.User;
import org.example.blackholetourismagencybook.auth.service.JwtService;
import org.example.blackholetourismagencybook.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/auth")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserCreatedResponse> register(@Valid @RequestBody UserCreateRequest request){
        UserCreatedResponse response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        User user =  userService.verifyUser(request.getUsername(),request.getPassword());

        String token = jwtService.generateToken(request.getUsername(),user.getRole().name(),user.getStatus().name());

        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        LoginResponse LR = new LoginResponse();
        LR.setToken(token);
        LR.setUserResponse(response);

        return ResponseEntity.ok(LR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

}
