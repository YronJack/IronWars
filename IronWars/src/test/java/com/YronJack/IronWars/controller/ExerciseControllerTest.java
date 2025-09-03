package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.exercise.ExerciseResponseDTO;
import com.YronJack.IronWars.enums.Dificulty;
import com.YronJack.IronWars.service.interfaces.ExerciseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExerciseController.class)
@DisplayName("Exercise Controller Tests")
class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseService exerciseService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ExerciseResponseDTO> mockExercises;

    @BeforeEach
    void setUp() {
        mockExercises = createMockExercises();
    }

    private List<ExerciseResponseDTO> createMockExercises() {
        ExerciseResponseDTO exercise1 = new ExerciseResponseDTO();
        exercise1.setId(1L);
        exercise1.setTitle("FizzBuzz Challenge");
        exercise1.setDescription("Write a program that prints numbers from 1 to 100");
        exercise1.setStarterCode("public class FizzBuzz { }");
        exercise1.setSolution("// Solution here");
        exercise1.setDifficulty(Dificulty.Beginner);
        exercise1.setLanguageId(1L);
        exercise1.setLanguageName("Java");
        exercise1.setCreatedAt(LocalDateTime.now().minusDays(1));
        exercise1.setUpdatedAt(LocalDateTime.now());

        ExerciseResponseDTO exercise2 = new ExerciseResponseDTO();
        exercise2.setId(2L);
        exercise2.setTitle("Binary Search Algorithm");
        exercise2.setDescription("Implement binary search algorithm");
        exercise2.setStarterCode("public class BinarySearch { }");
        exercise2.setSolution("// Binary search solution");
        exercise2.setDifficulty(Dificulty.Junior);
        exercise2.setLanguageId(1L);
        exercise2.setLanguageName("Java");
        exercise2.setCreatedAt(LocalDateTime.now().minusDays(2));
        exercise2.setUpdatedAt(LocalDateTime.now().minusHours(1));

        ExerciseResponseDTO exercise3 = new ExerciseResponseDTO();
        exercise3.setId(3L);
        exercise3.setTitle("Dynamic Programming - Fibonacci");
        exercise3.setDescription("Optimize Fibonacci sequence using dynamic programming");
        exercise3.setStarterCode("def fibonacci(n): pass");
        exercise3.setSolution("# DP solution here");
        exercise3.setDifficulty(Dificulty.Senior);
        exercise3.setLanguageId(2L);
        exercise3.setLanguageName("Python");
        exercise3.setCreatedAt(LocalDateTime.now().minusDays(3));
        exercise3.setUpdatedAt(LocalDateTime.now().minusHours(2));

        return Arrays.asList(exercise1, exercise2, exercise3);
    }

    @Test
    @DisplayName("GET /api/exercises/difficulty/Beginner should return 200 with beginner exercises")
    void getExercisesByDifficulty_Beginner_ShouldReturn200WithBeginnerExercises() throws Exception {
        // Arrange
        List<ExerciseResponseDTO> beginnerExercises = mockExercises.stream()
                .filter(ex -> ex.getDifficulty() == Dificulty.Beginner)
                .toList();
        when(exerciseService.getExercisesByDifficulty(Dificulty.Beginner))
                .thenReturn(beginnerExercises);

        // Act & Assert
        mockMvc.perform(get("/api/exercises/difficulty/Beginner")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("FizzBuzz Challenge")))
                .andExpect(jsonPath("$[0].difficulty", is("Beginner")))
                .andExpect(jsonPath("$[0].languageId", is(1)))
                .andExpect(jsonPath("$[0].languageName", is("Java")));

        verify(exerciseService).getExercisesByDifficulty(Dificulty.Beginner);
    }

    @Test
    @DisplayName("GET /api/exercises/difficulty/Junior should return 200 with junior exercises")
    void getExercisesByDifficulty_Junior_ShouldReturn200WithJuniorExercises() throws Exception {
        // Arrange
        List<ExerciseResponseDTO> juniorExercises = mockExercises.stream()
                .filter(ex -> ex.getDifficulty() == Dificulty.Junior)
                .toList();
        when(exerciseService.getExercisesByDifficulty(Dificulty.Junior))
                .thenReturn(juniorExercises);

        // Act & Assert
        mockMvc.perform(get("/api/exercises/difficulty/Junior")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].title", is("Binary Search Algorithm")))
                .andExpect(jsonPath("$[0].difficulty", is("Junior")))
                .andExpect(jsonPath("$[0].languageId", is(1)))
                .andExpect(jsonPath("$[0].languageName", is("Java")));

        verify(exerciseService).getExercisesByDifficulty(Dificulty.Junior);
    }

    @Test
    @DisplayName("GET /api/exercises/difficulty/Senior should return 200 with senior exercises")
    void getExercisesByDifficulty_Senior_ShouldReturn200WithSeniorExercises() throws Exception {
        // Arrange
        List<ExerciseResponseDTO> seniorExercises = mockExercises.stream()
                .filter(ex -> ex.getDifficulty() == Dificulty.Senior)
                .toList();
        when(exerciseService.getExercisesByDifficulty(Dificulty.Senior))
                .thenReturn(seniorExercises);

        // Act & Assert
        mockMvc.perform(get("/api/exercises/difficulty/Senior")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].title", is("Dynamic Programming - Fibonacci")))
                .andExpect(jsonPath("$[0].difficulty", is("Senior")))
                .andExpect(jsonPath("$[0].languageId", is(2)))
                .andExpect(jsonPath("$[0].languageName", is("Python")));

        verify(exerciseService).getExercisesByDifficulty(Dificulty.Senior);
    }

    @Test
    @DisplayName("GET /api/exercises/difficulty/Master should return 200 with empty list when no master exercises")
    void getExercisesByDifficulty_Master_ShouldReturn200WithEmptyList() throws Exception {
        // Arrange
        when(exerciseService.getExercisesByDifficulty(Dificulty.Master))
                .thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/exercises/difficulty/Master")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(exerciseService).getExercisesByDifficulty(Dificulty.Master);
    }

    @Test
    @DisplayName("GET /api/exercises/language/1 should return 200 with Java exercises")
    void getExercisesByLanguage_Java_ShouldReturn200WithJavaExercises() throws Exception {
        // Arrange
        List<ExerciseResponseDTO> javaExercises = mockExercises.stream()
                .filter(ex -> ex.getLanguageId().equals(1L))
                .toList();
        when(exerciseService.getExercisesByLanguage(1L))
                .thenReturn(javaExercises);

        // Act & Assert
        mockMvc.perform(get("/api/exercises/language/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].languageId", everyItem(is(1))))
                .andExpect(jsonPath("$[*].languageName", everyItem(is("Java"))))
                .andExpect(jsonPath("$[0].title", is("FizzBuzz Challenge")))
                .andExpect(jsonPath("$[1].title", is("Binary Search Algorithm")));

        verify(exerciseService).getExercisesByLanguage(1L);
    }

    @Test
    @DisplayName("GET /api/exercises/language/2 should return 200 with Python exercises")
    void getExercisesByLanguage_Python_ShouldReturn200WithPythonExercises() throws Exception {
        // Arrange
        List<ExerciseResponseDTO> pythonExercises = mockExercises.stream()
                .filter(ex -> ex.getLanguageId().equals(2L))
                .toList();
        when(exerciseService.getExercisesByLanguage(2L))
                .thenReturn(pythonExercises);

        // Act & Assert
        mockMvc.perform(get("/api/exercises/language/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].languageId", is(2)))
                .andExpect(jsonPath("$[0].languageName", is("Python")))
                .andExpect(jsonPath("$[0].title", is("Dynamic Programming - Fibonacci")));

        verify(exerciseService).getExercisesByLanguage(2L);
    }

    @Test
    @DisplayName("GET /api/exercises/language/99 should return 200 with empty list for non-existent language")
    void getExercisesByLanguage_NonExistentLanguage_ShouldReturn200WithEmptyList() throws Exception {
        // Arrange
        when(exerciseService.getExercisesByLanguage(99L))
                .thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/exercises/language/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(exerciseService).getExercisesByLanguage(99L);
    }

    @Test
    @DisplayName("GET /api/exercises should return 200 with all exercises")
    void getAllExercises_ShouldReturn200WithAllExercises() throws Exception {
        // Arrange
        when(exerciseService.getAllExercises()).thenReturn(mockExercises);

        // Act & Assert
        mockMvc.perform(get("/api/exercises")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)));

        verify(exerciseService).getAllExercises();
    }

    @Test
    @DisplayName("GET /api/exercises should return 200 with empty list when no exercises exist")
    void getAllExercises_ShouldReturn200WithEmptyListWhenNoExercises() throws Exception {
        // Arrange
        when(exerciseService.getAllExercises()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/exercises")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(exerciseService).getAllExercises();
    }

    @Test
    @DisplayName("GET /api/exercises/difficulty/Beginner should verify response structure and content")
    void getExercisesByDifficulty_Beginner_ShouldVerifyResponseStructure() throws Exception {
        // Arrange
        List<ExerciseResponseDTO> beginnerExercises = mockExercises.stream()
                .filter(ex -> ex.getDifficulty() == Dificulty.Beginner)
                .toList();
        when(exerciseService.getExercisesByDifficulty(Dificulty.Beginner))
                .thenReturn(beginnerExercises);

        // Act & Assert
        mockMvc.perform(get("/api/exercises/difficulty/Beginner"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].title", notNullValue()))
                .andExpect(jsonPath("$[0].description", notNullValue()))
                .andExpect(jsonPath("$[0].starterCode", notNullValue()))
                .andExpect(jsonPath("$[0].solution", notNullValue()))
                .andExpect(jsonPath("$[0].difficulty", notNullValue()))
                .andExpect(jsonPath("$[0].languageId", notNullValue()))
                .andExpect(jsonPath("$[0].languageName", notNullValue()))
                .andExpect(jsonPath("$[0].createdAt", notNullValue()))
                .andExpect(jsonPath("$[0].updatedAt", notNullValue()));

        verify(exerciseService).getExercisesByDifficulty(Dificulty.Beginner);
    }

    @Test
    @DisplayName("GET /api/exercises/language/1 should verify response structure for language filtering")
    void getExercisesByLanguage_ShouldVerifyResponseStructure() throws Exception {
        // Arrange
        List<ExerciseResponseDTO> javaExercises = mockExercises.stream()
                .filter(ex -> ex.getLanguageId().equals(1L))
                .toList();
        when(exerciseService.getExercisesByLanguage(1L))
                .thenReturn(javaExercises);

        // Act & Assert
        mockMvc.perform(get("/api/exercises/language/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].title", notNullValue()))
                .andExpect(jsonPath("$[0].description", notNullValue()))
                .andExpect(jsonPath("$[0].starterCode", notNullValue()))
                .andExpect(jsonPath("$[0].solution", notNullValue()))
                .andExpect(jsonPath("$[0].difficulty", notNullValue()))
                .andExpect(jsonPath("$[0].languageId", notNullValue()))
                .andExpect(jsonPath("$[0].languageName", notNullValue()));

        verify(exerciseService).getExercisesByLanguage(1L);
    }
}