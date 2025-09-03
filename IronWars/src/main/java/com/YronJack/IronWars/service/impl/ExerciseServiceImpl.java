package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.dto.exercise.ExerciseRequestDTO;
import com.YronJack.IronWars.dto.exercise.ExerciseResponseDTO;
import com.YronJack.IronWars.enums.ExperienceLevel;
import com.YronJack.IronWars.model.Exercise;
import com.YronJack.IronWars.repository.ExerciseRepository;
import com.YronJack.IronWars.service.interfaces.ExerciseService;
import com.YronJack.IronWars.enums.Difficulty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

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
    public List<ExerciseResponseDTO> getExercisesByDifficulty(Difficulty difficulty) {
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

    @Override
    public List<Exercise> getExercisesByDifficultyAndLanguageId(Difficulty difficulty, Long languageId) {
        return exerciseRepository.findByDifficultyAndLanguageId(difficulty, languageId);
    }

    public static List<Exercise> fillExamWithRandomExercises(ExperienceLevel experienceLevel, Long languageId) throws Exception {
        ExerciseServiceImpl service = new ExerciseServiceImpl();
        Random rand = new Random();
        Difficulty difficulty = Difficulty.valueOf(experienceLevel.toString());
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
