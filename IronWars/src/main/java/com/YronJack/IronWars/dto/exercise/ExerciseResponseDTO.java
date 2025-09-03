package com.YronJack.IronWars.dto.exercise;

import com.YronJack.IronWars.enums.Difficulty;
import com.YronJack.IronWars.model.Exercise;
import com.YronJack.IronWars.model.Language;
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

    public static ExerciseResponseDTO fromEntity(Exercise e) {
        ExerciseResponseDTO dto = new ExerciseResponseDTO();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setStarterCode(e.getStarterCode());
        dto.setSolution(e.getSolution());
        dto.setDifficulty(e.getDifficulty());
        Language lang = e.getLanguage();
        if (lang != null) {
            dto.setLanguageId(lang.getId());
            dto.setLanguageName(lang.getName());
        }
        dto.setCreatedAt(e.getCreatedAt());
        dto.setUpdatedAt(e.getUpdatedAt());
        return dto;
    }
}