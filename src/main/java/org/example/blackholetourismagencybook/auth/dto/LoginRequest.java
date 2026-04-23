package org.example.blackholetourismagencybook.auth.dto;


import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class LoginRequest {
    private String username;
    private String password;
}
