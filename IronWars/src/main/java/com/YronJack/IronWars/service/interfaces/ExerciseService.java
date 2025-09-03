package com.YronJack.IronWars.service.interfaces;

import com.YronJack.IronWars.dto.exercise.ExerciseRequestDTO;
import com.YronJack.IronWars.dto.exercise.ExerciseResponseDTO;

import com.YronJack.IronWars.enums.Difficulty;
import com.YronJack.IronWars.model.Exercise;

import java.util.List;

public interface ExerciseService {
    ExerciseResponseDTO createExercise(ExerciseRequestDTO exerciseRequestDTO);
    ExerciseResponseDTO getExerciseById(Long id);
    List<ExerciseResponseDTO> getAllExercises();
    List<ExerciseResponseDTO> getExercisesByDifficulty(Difficulty difficulty);
    List<ExerciseResponseDTO> getExercisesByLanguage(Long languageId);
    ExerciseResponseDTO updateExercise(Long id, ExerciseRequestDTO exerciseRequestDTO);
    void deleteExercise(Long id);
    List<Exercise> getExercisesByDifficultyAndLanguageId(Difficulty difficulty, Long languageId);


}