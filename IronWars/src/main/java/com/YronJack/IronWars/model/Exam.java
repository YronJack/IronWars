package com.YronJack.IronWars.model;

import com.YronJack.IronWars.enums.Dificulty;
import com.YronJack.IronWars.enums.Score;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;


@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exam_id;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Exercise> exercises;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id", nullable = false)
    @NotNull(message= "The Language is mandatory")
    private Language language;

    @ManyToOne
    private Student student;

    @Enumerated(EnumType.STRING)
    private Dificulty dificulty;

    private LocalTime startTime;

    private LocalTime endTime;

    @NotNull(message = "Duration is mandatory")
    private Duration duration;

    @Enumerated(EnumType.STRING)
    private Score score;





    public Exam (Language language, Student student) throws Exception {
        this.language = language;
        this.student = student;
        this.endTime = null;
        this.startTime = now();
        this.duration = Duration.ofMinutes(20);
        this.exercises = new ArrayList<>();
        this.exercises = Exercise.fillExamWithRandomExercises( student.getExperienceLevel(),language.getId());
    }
}

