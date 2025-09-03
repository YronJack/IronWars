package com.YronJack.IronWars.dto.exercise;

import com.YronJack.IronWars.enums.Difficulty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExerciseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String starterCode;
    private String solution;
    private Difficulty difficulty;
    private Long languageId;
    private String languageName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

