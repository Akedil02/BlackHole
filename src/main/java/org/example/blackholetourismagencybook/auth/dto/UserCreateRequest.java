package org.example.blackholetourismagencybook.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.blackholetourismagencybook.auth.entity.User;
import org.example.blackholetourismagencybook.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserCreateRequest {
    @NotNull(message = "Username should not be empty")
    private String  username;

    @Size(min = 6, message = "Password should at least contain 6 characters")
    private String password;

    @Email(message = "Email format is not correct")
    private String email;
}
