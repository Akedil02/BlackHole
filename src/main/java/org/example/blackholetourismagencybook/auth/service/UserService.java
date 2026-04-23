package org.example.blackholetourismagencybook.auth.service;

import org.example.blackholetourismagencybook.auth.dto.UserCreateRequest;
import org.example.blackholetourismagencybook.auth.dto.UserCreatedResponse;
import org.example.blackholetourismagencybook.auth.dto.UserResponse;
import org.example.blackholetourismagencybook.auth.entity.User;
import org.example.blackholetourismagencybook.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Create
    public UserCreatedResponse createUser(UserCreateRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        UserCreatedResponse response = new UserCreatedResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setCreatedAt(LocalDateTime.now());

        return response;
    }

    //Verify user

    public UserResponse verifyUser(String username, String passwd){
        User user = getUserByUsername(username);

        if(!passwordEncoder.matches(passwd,user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        return response;
    }



    //GetByUsername
    public User getUserByUsername(String username){
        User user = userRepository.findByUsername(username);

        if(user == null) {
            throw new RuntimeException("User not found");
        }

        return user;
    }


    //GetById
    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        return response;
    }

}
