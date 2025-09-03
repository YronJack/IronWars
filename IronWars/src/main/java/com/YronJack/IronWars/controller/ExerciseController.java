package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.exercise.ExerciseRequestDTO;
import com.YronJack.IronWars.dto.exercise.ExerciseResponseDTO;
import com.YronJack.IronWars.enums.Dificulty;
import com.YronJack.IronWars.service.interfaces.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    @Autowired
    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseResponseDTO> createExercise(
            @Valid @RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        ExerciseResponseDTO createdExercise = exerciseService.createExercise(exerciseRequestDTO);
        return ResponseEntity.ok(createdExercise);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> getExercise(@PathVariable Long id) {
        ExerciseResponseDTO exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(exercise);
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDTO>> getAllExercises() {
        List<ExerciseResponseDTO> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<ExerciseResponseDTO>> getExercisesByDifficulty(
            @PathVariable Dificulty difficulty) {
        List<ExerciseResponseDTO> exercises = exerciseService.getExercisesByDifficulty(difficulty);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/language/{languageId}")
    public ResponseEntity<List<ExerciseResponseDTO>> getExercisesByLanguage(
            @PathVariable Long languageId) {
        List<ExerciseResponseDTO> exercises = exerciseService.getExercisesByLanguage(languageId);
        return ResponseEntity.ok(exercises);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> updateExercise(
            @PathVariable Long id,
            @Valid @RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        ExerciseResponseDTO updatedExercise = exerciseService.updateExercise(id, exerciseRequestDTO);
        return ResponseEntity.ok(updatedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}