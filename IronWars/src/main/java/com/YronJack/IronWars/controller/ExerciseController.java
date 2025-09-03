package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.exercise.ExerciseRequestDTO;
import com.YronJack.IronWars.dto.exercise.ExerciseResponseDTO;
import com.YronJack.IronWars.enums.Difficulty;
import com.YronJack.IronWars.service.interfaces.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
@Tag(name = "Exercises", description = "Operations related to exercises")
public class ExerciseController {

    @Autowired
    private final ExerciseService exerciseService;

    @Operation(summary = "Create a new exercise")
    @ApiResponse(responseCode = "200", description = "Exercise created successfully")
    @PostMapping
    public ResponseEntity<ExerciseResponseDTO> createExercise(
            @Valid @RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        ExerciseResponseDTO createdExercise = exerciseService.createExercise(exerciseRequestDTO);
        return ResponseEntity.ok(createdExercise);
    }

    @Operation(summary = "Get an exercise by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exercise found"),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> getExercise(
            @Parameter(description = "ID of the exercise to retrieve") @PathVariable Long id) {
        ExerciseResponseDTO exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(exercise);
    }

    @Operation(summary = "Get all exercises")
    @ApiResponse(responseCode = "200", description = "List of exercises")
    @GetMapping
    public ResponseEntity<List<ExerciseResponseDTO>> getAllExercises() {
        List<ExerciseResponseDTO> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @Operation(summary = "Get exercises by difficulty")
    @ApiResponse(responseCode = "200", description = "List of exercises by difficulty")
    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<ExerciseResponseDTO>> getExercisesByDifficulty(
            @Parameter(description = "Difficulty level") @PathVariable Difficulty difficulty) {
        List<ExerciseResponseDTO> exercises = exerciseService.getExercisesByDifficulty(difficulty);
        return ResponseEntity.ok(exercises);
    }

    @Operation(summary = "Get exercises by language")
    @ApiResponse(responseCode = "200", description = "List of exercises by language")
    @GetMapping("/language/{languageId}")
    public ResponseEntity<List<ExerciseResponseDTO>> getExercisesByLanguage(
            @Parameter(description = "Language ID") @PathVariable Long languageId) {
        List<ExerciseResponseDTO> exercises = exerciseService.getExercisesByLanguage(languageId);
        return ResponseEntity.ok(exercises);
    }

    @Operation(summary = "Update an exercise by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exercise updated"),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> updateExercise(
            @Parameter(description = "ID of the exercise to update") @PathVariable Long id,
            @Valid @RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        ExerciseResponseDTO updatedExercise = exerciseService.updateExercise(id, exerciseRequestDTO);
        return ResponseEntity.ok(updatedExercise);
    }

    @Operation(summary = "Delete an exercise by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Exercise deleted"),
            @ApiResponse(responseCode = "404", description = "Exercise not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(
            @Parameter(description = "ID of the exercise to delete") @PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}