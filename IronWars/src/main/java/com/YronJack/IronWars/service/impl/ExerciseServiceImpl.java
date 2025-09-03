package com.YronJack.IronWars.service;

import com.YronJack.IronWars.dto.exercise.ExerciseRequestDTO;
import com.YronJack.IronWars.dto.exercise.ExerciseResponseDTO;
import com.YronJack.IronWars.entity.Exercise;
import com.YronJack.IronWars.entity.Language;
import com.YronJack.IronWars.entity.enums.Difficulty;
import com.YronJack.IronWars.repository.ExerciseRepository;
import com.YronJack.IronWars.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final LanguageRepository languageRepository;

    @Override
    public ExerciseResponseDTO createExercise(ExerciseRequestDTO exerciseRequestDTO) {
        Language language = languageRepository.findById(exerciseRequestDTO.getLanguageId())
                .orElseThrow(() -> new RuntimeException("Language not found"));

        Exercise exercise = new Exercise();
        exercise.setTitle(exerciseRequestDTO.getTitle());
        exercise.setDescription(exerciseRequestDTO.getDescription());
        exercise.setStarterCode(exerciseRequestDTO.getStarterCode());
        exercise.setSolution(exerciseRequestDTO.getSolution());
        exercise.setDifficulty(exerciseRequestDTO.getDifficulty());
        exercise.setLanguage(language);

        Exercise savedExercise = exerciseRepository.save(exercise);
        return convertToDTO(savedExercise);
    }

    @Override
    public ExerciseResponseDTO getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
        return convertToDTO(exercise);
    }

    @Override
    public List<ExerciseResponseDTO> getAllExercises() {
        return exerciseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseResponseDTO> getExercisesByDifficulty(Difficulty difficulty) {
        return exerciseRepository.findByDifficulty(difficulty).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseResponseDTO> getExercisesByLanguage(Long languageId) {
        return exerciseRepository.findByLanguageId(languageId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExerciseResponseDTO updateExercise(Long id, ExerciseRequestDTO exerciseRequestDTO) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        Language language = languageRepository.findById(exerciseRequestDTO.getLanguageId())
                .orElseThrow(() -> new RuntimeException("Language not found"));

        exercise.setTitle(exerciseRequestDTO.getTitle());
        exercise.setDescription(exerciseRequestDTO.getDescription());
        exercise.setStarterCode(exerciseRequestDTO.getStarterCode());
        exercise.setSolution(exerciseRequestDTO.getSolution());
        exercise.setDifficulty(exerciseRequestDTO.getDifficulty());
        exercise.setLanguage(language);

        Exercise updatedExercise = exerciseRepository.save(exercise);
        return convertToDTO(updatedExercise);
    }

    @Override
    public void deleteExercise(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new RuntimeException("Exercise not found");
        }
        exerciseRepository.deleteById(id);
    }

    private ExerciseResponseDTO convertToDTO(Exercise exercise) {
        ExerciseResponseDTO dto = new ExerciseResponseDTO();
        dto.setId(exercise.getId());
        dto.setTitle(exercise.getTitle());
        dto.setDescription(exercise.getDescription());
        dto.setStarterCode(exercise.getStarterCode());
        dto.setSolution(exercise.getSolution());
        dto.setDifficulty(exercise.getDifficulty());
        dto.setLanguageId(exercise.getLanguage().getId());
        dto.setLanguageName(exercise.getLanguage().getName());
        dto.setCreatedAt(exercise.getCreatedAt());
        dto.setUpdatedAt(exercise.getUpdatedAt());
        return dto;
    }
}