package com.YronJack.IronWars.dto.student;

import com.YronJack.IronWars.enums.ExperienceLevel;
import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.enums.Score;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponseDTO {
    @NotNull
    private Long StudentId;

    private String nickName;

    private String name;

    private String lastName;

    private String address;

    private Score averageScore;

    private ExperienceLevel experienceLevel;

    private List<Exam> examList;
}
