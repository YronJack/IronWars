package com.YronJack.IronWars.dto.teacher;

import com.YronJack.IronWars.model.Language;
import com.YronJack.IronWars.model.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class TeacherResponseDTO {
    private Long id;

    private String name;

    private String email;

    private Integer yearsExperience;

    private Double rating;

    private List<String> languages;

    //Mapper

    public static TeacherResponseDTO fromEntity(Teacher teacher) {
        TeacherResponseDTO dto = new TeacherResponseDTO();
        dto.setId(teacher.getId());
        dto.setName(teacher.getPersonalData() != null ? teacher.getPersonalData().getName() : null);
        dto.setEmail(teacher.getPersonalData() != null ? teacher.getPersonalData().getEmail() : null);
        dto.setYearsExperience(teacher.getYearsExperience());
        dto.setRating(teacher.getRating());
        dto.setLanguages(teacher.getLanguages() !=null
                ? teacher.getLanguages().stream().map(Language::getName).toList()
                : List.of()
        ); return dto;
    }
}