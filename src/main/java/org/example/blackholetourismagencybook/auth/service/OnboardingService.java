package org.example.blackholetourismagencybook.auth.service;

import jakarta.transaction.Transactional;
import org.example.blackholetourismagencybook.auth.dto.QuestionnaireDTO;
import org.example.blackholetourismagencybook.auth.entity.User;
import org.example.blackholetourismagencybook.auth.entity.UserStatus;
import org.example.blackholetourismagencybook.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnboardingService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User submitQuestionnaire (Long userId, QuestionnaireDTO dto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(dto.isHasCardiovascularDisease() || !dto.isHasEarthEstateManager()){
            user.setStatus(UserStatus.RESTRICTED);
        }

        else {
            user.setStatus(UserStatus.ACTIVE);
        }

        return user;
    }


}


