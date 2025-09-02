package com.YronJack.IronWars.model;

import com.YronJack.IronWars.unums.Score;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_exam",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> examList;
}
