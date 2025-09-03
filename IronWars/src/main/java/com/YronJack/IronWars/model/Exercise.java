package com.YronJack.IronWars.model;

import com.YronJack.IronWars.dto.exercise.ExerciseResponseDTO;
import com.YronJack.IronWars.enums.Dificulty;

import com.YronJack.IronWars.enums.ExperienceLevel;
import com.YronJack.IronWars.repository.ExerciseRepository;
import com.YronJack.IronWars.service.impl.ExerciseServiceImpl;
import com.YronJack.IronWars.service.interfaces.ExerciseService;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.AnnotatedArrayType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Data
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String starterCode;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String solution;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Dificulty dificulty;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;



    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    public static List<Exercise> fillExamWithRandomExercises(ExperienceLevel experienceLevel, Long languageId) throws Exception {
        ExerciseServiceImpl service = new ExerciseServiceImpl();
        Random rand = new Random();
        Dificulty difficulty = Dificulty.valueOf(experienceLevel.toString());
        List<Exercise> randomExercises = service.getExercisesByDifficultyAndLanguageId(difficulty, languageId);

        if (randomExercises.isEmpty()) {
            throw new Exception("No exercises found");
        }

        List<Exercise> examReady = new ArrayList<>();
        List<Exercise> copyList = new ArrayList<>(randomExercises);

        int count = Math.min(10, copyList.size());
        for (int i = 0; i < count; i++) {
            int index = rand.nextInt(copyList.size());
            examReady.add(copyList.remove(index));
        }

        return examReady;
    }

}