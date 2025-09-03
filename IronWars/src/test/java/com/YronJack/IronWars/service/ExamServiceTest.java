package com.YronJack.IronWars.service;

import com.YronJack.IronWars.enums.Difficulty;
import com.YronJack.IronWars.enums.Score;
import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Exercise;
import com.YronJack.IronWars.model.Language;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.repository.ExamRepository;
import com.YronJack.IronWars.repository.ExerciseRepository;
import com.YronJack.IronWars.service.impl.ExamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ExamService Tests - N:M Relationship Management")
class ExamServiceTest {

    @Mock
    private ExamRepository examRepository;

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExamServiceImpl examService;

    private Exam mockExam;
    private Exercise mockExercise1;
    private Exercise mockExercise2;
    private Language mockLanguage;
    private Student mockStudent;

    @BeforeEach
    void setUp() {
        // Setup mock language
        mockLanguage = new Language();
        mockLanguage.setId(1L);
        mockLanguage.setName("Java");

        // Setup mock student
        mockStudent = new Student();
        mockStudent.setId(1L);

        // Setup mock exercises
        mockExercise1 = new Exercise();
        mockExercise1.setId(1L);
        mockExercise1.setTitle("FizzBuzz Challenge");
        mockExercise1.setDescription("Write a program that prints numbers from 1 to 100");
        mockExercise1.setStarterCode("public class FizzBuzz { }");
        mockExercise1.setSolution("// Solution here");
        mockExercise1.setDifficulty(Difficulty.Beginner);
        mockExercise1.setLanguage(mockLanguage);
        mockExercise1.setCreatedAt(LocalDateTime.now());
        mockExercise1.setUpdatedAt(LocalDateTime.now());

        mockExercise2 = new Exercise();
        mockExercise2.setId(2L);
        mockExercise2.setTitle("Binary Search Algorithm");
        mockExercise2.setDescription("Implement binary search algorithm");
        mockExercise2.setStarterCode("public class BinarySearch { }");
        mockExercise2.setSolution("// Binary search solution");
        mockExercise2.setDifficulty(Difficulty.Junior);
        mockExercise2.setLanguage(mockLanguage);
        mockExercise2.setCreatedAt(LocalDateTime.now());
        mockExercise2.setUpdatedAt(LocalDateTime.now());

        // Setup mock exam
        mockExam = new Exam();
        mockExam.setExam_id(1L);
        mockExam.setLanguage(mockLanguage);
        mockExam.setStudent(mockStudent);
        mockExam.setDificulty(Difficulty.Junior);
        mockExam.setStartTime(LocalTime.now());
        mockExam.setDuration(60);
        mockExam.setScore(Score.Null);
        mockExam.setExercises(new ArrayList<>());
    }

    @Test
    @DisplayName("Should add exercise to exam successfully")
    void addExerciseToExam_ShouldAddSuccessfully() {
        // Arrange
        Long examId = 1L;
        Long exerciseId = 1L;
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(mockExercise1));
        when(examRepository.save(any(Exam.class))).thenReturn(mockExam);

        // Act
        Optional<Exam> result = examService.addExerciseToExam(examId, exerciseId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getExercises().size());
        assertTrue(result.get().getExercises().contains(mockExercise1));
        
        verify(examRepository).findById(examId);
        verify(exerciseRepository).findById(exerciseId);
        verify(examRepository).save(mockExam);
    }

    @Test
    @DisplayName("Should handle null exercises list when adding exercise")
    void addExerciseToExam_WithNullExercisesList_ShouldInitializeAndAdd() {
        // Arrange
        Long examId = 1L;
        Long exerciseId = 1L;
        mockExam.setExercises(null); // Start with null exercises list
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(mockExercise1));
        when(examRepository.save(any(Exam.class))).thenReturn(mockExam);

        // Act
        Optional<Exam> result = examService.addExerciseToExam(examId, exerciseId);

        // Assert
        assertTrue(result.isPresent());
        assertNotNull(result.get().getExercises());
        assertEquals(1, result.get().getExercises().size());
        assertTrue(result.get().getExercises().contains(mockExercise1));
        
        verify(examRepository).findById(examId);
        verify(exerciseRepository).findById(exerciseId);
        verify(examRepository).save(mockExam);
    }

    @Test
    @DisplayName("Should not add duplicate exercise to exam")
    void addExerciseToExam_WithDuplicateExercise_ShouldNotAddDuplicate() {
        // Arrange
        Long examId = 1L;
        Long exerciseId = 1L;
        mockExam.getExercises().add(mockExercise1); // Exercise already in exam
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(mockExercise1));

        // Act
        Optional<Exam> result = examService.addExerciseToExam(examId, exerciseId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getExercises().size());
        
        verify(examRepository).findById(examId);
        verify(exerciseRepository).findById(exerciseId);
        verify(examRepository, never()).save(any(Exam.class)); // Should not save if no changes
    }

    @Test
    @DisplayName("Should return empty optional when exam not found")
    void addExerciseToExam_ExamNotFound_ShouldReturnEmpty() {
        // Arrange
        Long examId = 99L;
        Long exerciseId = 1L;
        
        when(examRepository.findById(examId)).thenReturn(Optional.empty());
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(mockExercise1));

        // Act
        Optional<Exam> result = examService.addExerciseToExam(examId, exerciseId);

        // Assert
        assertFalse(result.isPresent());
        
        verify(examRepository).findById(examId);
        verify(exerciseRepository).findById(exerciseId);
        verify(examRepository, never()).save(any(Exam.class));
    }

    @Test
    @DisplayName("Should return empty optional when exercise not found")
    void addExerciseToExam_ExerciseNotFound_ShouldReturnEmpty() {
        // Arrange
        Long examId = 1L;
        Long exerciseId = 99L;
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.empty());

        // Act
        Optional<Exam> result = examService.addExerciseToExam(examId, exerciseId);

        // Assert
        assertFalse(result.isPresent());
        
        verify(examRepository).findById(examId);
        verify(exerciseRepository).findById(exerciseId);
        verify(examRepository, never()).save(any(Exam.class));
    }

    @Test
    @DisplayName("Should remove exercise from exam successfully")
    void removeExerciseFromExam_ShouldRemoveSuccessfully() {
        // Arrange
        Long examId = 1L;
        Long exerciseId = 1L;
        mockExam.getExercises().add(mockExercise1);
        mockExam.getExercises().add(mockExercise2);
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(mockExercise1));
        when(examRepository.save(any(Exam.class))).thenReturn(mockExam);

        // Act
        Optional<Exam> result = examService.removeExerciseFromExam(examId, exerciseId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getExercises().size());
        assertFalse(result.get().getExercises().contains(mockExercise1));
        assertTrue(result.get().getExercises().contains(mockExercise2));
        
        verify(examRepository).findById(examId);
        verify(exerciseRepository).findById(exerciseId);
        verify(examRepository).save(mockExam);
    }

    @Test
    @DisplayName("Should handle removal when exercise not in exam")
    void removeExerciseFromExam_ExerciseNotInExam_ShouldReturnExamWithoutChanges() {
        // Arrange
        Long examId = 1L;
        Long exerciseId = 2L;
        mockExam.getExercises().add(mockExercise1); // Only exercise1 in exam
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(mockExercise2));

        // Act
        Optional<Exam> result = examService.removeExerciseFromExam(examId, exerciseId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getExercises().size());
        assertTrue(result.get().getExercises().contains(mockExercise1));
        
        verify(examRepository).findById(examId);
        verify(exerciseRepository).findById(exerciseId);
        verify(examRepository, never()).save(any(Exam.class)); // Should not save if no changes
    }

    @Test
    @DisplayName("Should handle removal when exercises list is null")
    void removeExerciseFromExam_WithNullExercisesList_ShouldReturnExam() {
        // Arrange
        Long examId = 1L;
        Long exerciseId = 1L;
        mockExam.setExercises(null);
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(mockExercise1));

        // Act
        Optional<Exam> result = examService.removeExerciseFromExam(examId, exerciseId);

        // Assert
        assertTrue(result.isPresent());
        
        verify(examRepository).findById(examId);
        verify(exerciseRepository).findById(exerciseId);
        verify(examRepository, never()).save(any(Exam.class));
    }

    @Test
    @DisplayName("Should return empty optional when removing exercise from non-existent exam")
    void removeExerciseFromExam_ExamNotFound_ShouldReturnEmpty() {
        // Arrange
        Long examId = 99L;
        Long exerciseId = 1L;
        
        when(examRepository.findById(examId)).thenReturn(Optional.empty());
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(mockExercise1));

        // Act
        Optional<Exam> result = examService.removeExerciseFromExam(examId, exerciseId);

        // Assert
        assertFalse(result.isPresent());
        
        verify(examRepository).findById(examId);
        verify(exerciseRepository).findById(exerciseId);
        verify(examRepository, never()).save(any(Exam.class));
    }

    @Test
    @DisplayName("Should get exercises by exam ID successfully")
    void getExercisesByExamId_ShouldReturnExercises() {
        // Arrange
        Long examId = 1L;
        mockExam.getExercises().add(mockExercise1);
        mockExam.getExercises().add(mockExercise2);
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));

        // Act
        List<Exercise> result = examService.getExercisesByExamId(examId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(mockExercise1));
        assertTrue(result.contains(mockExercise2));
        
        verify(examRepository).findById(examId);
    }

    @Test
    @DisplayName("Should return empty list when exam has no exercises")
    void getExercisesByExamId_ExamWithNoExercises_ShouldReturnEmptyList() {
        // Arrange
        Long examId = 1L;
        mockExam.setExercises(null);
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));

        // Act
        List<Exercise> result = examService.getExercisesByExamId(examId);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        
        verify(examRepository).findById(examId);
    }

    @Test
    @DisplayName("Should return empty list when exam not found")
    void getExercisesByExamId_ExamNotFound_ShouldReturnEmptyList() {
        // Arrange
        Long examId = 99L;
        
        when(examRepository.findById(examId)).thenReturn(Optional.empty());

        // Act
        List<Exercise> result = examService.getExercisesByExamId(examId);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        
        verify(examRepository).findById(examId);
    }

    @Test
    @DisplayName("Should verify N:M relationship persistence through multiple operations")
    void verifyNtoMRelationshipPersistence_MultipleOperations() {
        // Arrange
        Long examId = 1L;
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(mockExercise1));
        when(exerciseRepository.findById(2L)).thenReturn(Optional.of(mockExercise2));
        when(examRepository.save(any(Exam.class))).thenReturn(mockExam);

        // Act & Assert - Add first exercise
        Optional<Exam> result1 = examService.addExerciseToExam(examId, 1L);
        assertTrue(result1.isPresent());
        assertEquals(1, result1.get().getExercises().size());

        // Act & Assert - Add second exercise
        Optional<Exam> result2 = examService.addExerciseToExam(examId, 2L);
        assertTrue(result2.isPresent());
        assertEquals(2, result2.get().getExercises().size());

        // Act & Assert - Remove first exercise
        Optional<Exam> result3 = examService.removeExerciseFromExam(examId, 1L);
        assertTrue(result3.isPresent());
        assertEquals(1, result3.get().getExercises().size());
        assertFalse(result3.get().getExercises().contains(mockExercise1));
        assertTrue(result3.get().getExercises().contains(mockExercise2));

        // Verify all interactions
        verify(examRepository, times(3)).findById(examId);
        verify(exerciseRepository, times(2)).findById(1L);
        verify(exerciseRepository).findById(2L);
        verify(examRepository, times(3)).save(mockExam);
    }

    @Test
    @DisplayName("Should validate database persistence through repository save calls")
    void validateDatabasePersistence_RepositorySaveCalls() {
        // Arrange
        Long examId = 1L;
        Long exerciseId = 1L;
        
        when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(mockExercise1));
        when(examRepository.save(any(Exam.class))).thenReturn(mockExam);

        // Act
        examService.addExerciseToExam(examId, exerciseId);

        // Assert - Verify database interaction
        verify(examRepository).save(argThat(exam -> 
            exam.getExercises().contains(mockExercise1) && 
            exam.getExercises().size() == 1
        ));
    }
}