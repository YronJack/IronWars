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


    @Enumerated
    private Score averageScore;

    private Long experienceLevel;

    @OneToMany(mappedBy = "exam_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> examList;
}
