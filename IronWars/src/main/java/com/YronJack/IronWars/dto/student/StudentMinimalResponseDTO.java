package com.YronJack.IronWars.dto.student;

import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.unums.Score;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StudentMinimalResponseDTO {
    @NotNull
    private Long studentId;

    private String nickName;

    private Score averageScore;

    private Long experienceLevel;

    private List<Exam> examList;
}
