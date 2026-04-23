package org.example.blackholetourismagencybook.auth.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Data
public class UserCreatedResponse {
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
