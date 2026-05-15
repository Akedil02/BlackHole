package org.example.blackholetourismagencybook.auth.dto;


import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class QuestionnaireDTO {
    private boolean hasCardiovascularDisease;
    private boolean hasEarthEstateManager;
}
