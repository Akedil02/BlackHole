package org.example.blackholetourismagencybook.auth.controller;


import org.example.blackholetourismagencybook.auth.dto.QuestionnaireDTO;
import org.example.blackholetourismagencybook.auth.entity.User;
import org.example.blackholetourismagencybook.auth.entity.UserStatus;
import org.example.blackholetourismagencybook.auth.repository.UserRepository;
import org.example.blackholetourismagencybook.auth.service.JwtService;
import org.example.blackholetourismagencybook.auth.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {
    @Autowired
    private OnboardingService onboardingService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/questionnaire")
    public ResponseEntity<?> submitQuestionnaire(@RequestBody QuestionnaireDTO dto, Principal principal){
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User updatedUser = onboardingService.submitQuestionnaire(user.getId(), dto);

        if(updatedUser.getStatus() == UserStatus.RESTRICTED){
            Map<String, Object> restrictedResponse = new HashMap<>();
            restrictedResponse.put("message", "Questionnaire Review Completed, but your current status is restricted, please contact our customer service.");
            restrictedResponse.put("status", updatedUser.getStatus().name());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(restrictedResponse);

        }

        else {
            String newToken = jwtService.generateToken(
                    updatedUser.getUsername(),
                    updatedUser.getRole().name(),
                    updatedUser.getStatus().name()
            );

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Questionnaire Review Completed.");
            response.put("status", updatedUser.getStatus().name());
            response.put("token", newToken);

            return ResponseEntity.ok(response);
        }


    }




}
