package com.YronJack.IronWars.dto.teacher;

import com.YronJack.IronWars.model.PersonalData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TeacherRequestDTO {
    @NotBlank(message="Password is required")
    @Size(min = 8, max = 100,"The password must be between 8 and 100 characters")
    private String password;

    @Valid
    @NotNull(message = "PersonalData is required")
    private PersonalData personalData;

    private Integer yearsExperience;

    private Double rating;
}
