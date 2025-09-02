package com.YronJack.IronWars.dto.student;

import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.unums.Score;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    private Long experienceLevel;

    private List<Exam> examList;
}
