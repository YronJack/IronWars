package com.YronJack.IronWars.model;

import com.YronJack.IronWars.enums.Score;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {


    @Enumerated(EnumType.STRING)
    private Score averageScore;

    private Long experienceLevel;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Exam> examList;

    //Profesor asignado al estudiante
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
