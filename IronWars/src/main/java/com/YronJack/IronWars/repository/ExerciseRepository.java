package com.YronJack.IronWars.repository;

import com.YronJack.IronWars.model.Exercise;
import com.YronJack.IronWars.enums.Dificulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByDifficulty(Dificulty difficulty);
    List<Exercise> findByLanguageId(Long languageId);
    List<Exercise> findByDifficultyAndLanguageId(Dificulty difficulty, Long languageId);
}