package org.example.blackholetourismagencybook.auth.dto;


import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class LoginResponse {
    private String token;
    private UserResponse userResponse;
}
