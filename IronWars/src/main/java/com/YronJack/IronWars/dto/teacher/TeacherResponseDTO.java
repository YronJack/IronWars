package com.YronJack.IronWars.dto.teacher;

import com.YronJack.IronWars.model.Language;
import lombok.Data;

import java.util.List;

@Data
public class TeacherResponseDTO {
    private Long id;

    private String name;

    private String email;

    private Integer yearsExperience;

    private Double rating;

    private List<Language> languages;

}
