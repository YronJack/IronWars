package com.YronJack.IronWars.repository;

import com.YronJack.IronWars.model.Exercise;
import com.YronJack.IronWars.enums.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByDifficulty(Difficulty difficulty);
    List<Exercise> findByLanguageId(Long languageId);
    List<Exercise> findByDifficultyAndLanguageId(Difficulty difficulty, Long languageId);
}