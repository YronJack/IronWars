package com.YronJack.IronWars.dto.exercise;

import com.YronJack.IronWars.enums.Dificulty;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class ExerciseRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Starter code is required")
    private String starterCode;

    @NotBlank(message = "Solution is required")
    private String solution;

    @NotNull(message = "Difficulty is required")
    private Dificulty difficulty;

    @NotNull(message = "Language ID is required")
    private Long languageId;
}