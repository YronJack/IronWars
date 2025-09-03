package com.YronJack.IronWars.dto.teacher;

import com.YronJack.IronWars.model.PersonalData;
import com.YronJack.IronWars.model.Teacher;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TeacherRequestDTO {
    @NotBlank(message="Password is required")
    @Size(min = 8, max = 100,message = "The password must be between 8 and 100 characters")
    private String password;

    @Valid
    @NotNull(message = "PersonalData is required")
    private PersonalData personalData;

    private Integer yearsExperience;

    private Double rating;

    //mapper > Entity

    public Teacher toEntity(){
        Teacher t = new Teacher();
        t.setPassword(this.password);
        t.setPersonalData(this.personalData);
        t.setYearsExperience(this.yearsExperience);
        t.setRating(this.rating);
        return t;

    }
}