package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.dto.exercise.ExerciseRequestDTO;
import com.YronJack.IronWars.dto.exercise.ExerciseResponseDTO;
import com.YronJack.IronWars.service.interfaces.ExerciseService;
import com.YronJack.IronWars.unums.Dificulty;

import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {

    @Override
    public ExerciseResponseDTO createExercise(ExerciseRequestDTO exerciseRequestDTO) {
        return null;
    }

    @Override
    public ExerciseResponseDTO getExerciseById(Long id) {
        return null;
    }

    @Override
    public List<ExerciseResponseDTO> getAllExercises() {
        return List.of();
    }

    @Override
    public List<ExerciseResponseDTO> getExercisesByDifficulty(Dificulty difficulty) {
        return List.of();
    }

    @Override
    public List<ExerciseResponseDTO> getExercisesByLanguage(Long languageId) {
        return List.of();
    }

    @Override
    public ExerciseResponseDTO updateExercise(Long id, ExerciseRequestDTO exerciseRequestDTO) {
        return null;
    }

    @Override
    public void deleteExercise(Long id) {

    }
}
