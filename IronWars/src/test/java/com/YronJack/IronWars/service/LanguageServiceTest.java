package com.YronJack.IronWars.service;

import com.YronJack.IronWars.enums.ExperienceLevel;
import com.YronJack.IronWars.model.Language;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.model.Teacher;
import com.YronJack.IronWars.repository.LanguageRepository;
import com.YronJack.IronWars.repository.StudentRepository;
import com.YronJack.IronWars.repository.TeacherRepository;
import com.YronJack.IronWars.service.impl.LanguageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("LanguageService Tests - Expert and Student Listing by Level")
class LanguageServiceTest {

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private LanguageServiceImpl languageService;

    private Language mockJavaLanguage;
    private Language mockPythonLanguage;
    private Teacher mockTeacher1;
    private Teacher mockTeacher2;
    private Teacher mockTeacher3;
    private Student mockBeginnerStudent1;
    private Student mockBeginnerStudent2;
    private Student mockJuniorStudent1;
    private Student mockSeniorStudent1;
    private Student mockMasterStudent1;

    @BeforeEach
    void setUp() {
        // Setup mock languages
        mockJavaLanguage = new Language();
        mockJavaLanguage.setId(1L);
        mockJavaLanguage.setName("Java");
        mockJavaLanguage.setDomain("Programming");

        mockPythonLanguage = new Language();
        mockPythonLanguage.setId(2L);
        mockPythonLanguage.setName("Python");
        mockPythonLanguage.setDomain("Data Science");

        // Setup mock teachers
        mockTeacher1 = new Teacher();
        mockTeacher1.setId(1L);
        mockTeacher1.setYearsExperience(5);
        mockTeacher1.setRating(4.5);

        mockTeacher2 = new Teacher();
        mockTeacher2.setId(2L);
        mockTeacher2.setYearsExperience(8);
        mockTeacher2.setRating(4.8);

        mockTeacher3 = new Teacher();
        mockTeacher3.setId(3L);
        mockTeacher3.setYearsExperience(10);
        mockTeacher3.setRating(4.9);

        // Setup mock students with different experience levels
        mockBeginnerStudent1 = new Student();
        mockBeginnerStudent1.setId(1L);
        mockBeginnerStudent1.setExperienceLevel(ExperienceLevel.Beginner);

        mockBeginnerStudent2 = new Student();
        mockBeginnerStudent2.setId(2L);
        mockBeginnerStudent2.setExperienceLevel(ExperienceLevel.Beginner);

        mockJuniorStudent1 = new Student();
        mockJuniorStudent1.setId(3L);
        mockJuniorStudent1.setExperienceLevel(ExperienceLevel.Junior);

        mockSeniorStudent1 = new Student();
        mockSeniorStudent1.setId(4L);
        mockSeniorStudent1.setExperienceLevel(ExperienceLevel.Senior);

        mockMasterStudent1 = new Student();
        mockMasterStudent1.setId(5L);
        mockMasterStudent1.setExperienceLevel(ExperienceLevel.Master);
    }

    // ================ Expert Listing Tests ================

    @Test
    @DisplayName("Should list experts by language ID successfully")
    void getExpertsByLanguage_ValidLanguageId_ShouldReturnExperts() {
        // Arrange
        Long languageId = 1L;
        List<Teacher> expectedExperts = Arrays.asList(mockTeacher1, mockTeacher2);
        
        when(languageRepository.findById(languageId)).thenReturn(Optional.of(mockJavaLanguage));
        when(teacherRepository.findByLanguages_Id(languageId)).thenReturn(expectedExperts);

        // Act
        List<Teacher> result = languageService.getExpertsByLanguage(languageId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(mockTeacher1));
        assertTrue(result.contains(mockTeacher2));
        
        verify(languageRepository).findById(languageId);
        verify(teacherRepository).findByLanguages_Id(languageId);
    }

    @Test
    @DisplayName("Should return empty list when no experts found for language")
    void getExpertsByLanguage_NoExperts_ShouldReturnEmptyList() {
        // Arrange
        Long languageId = 2L;
        
        when(languageRepository.findById(languageId)).thenReturn(Optional.of(mockPythonLanguage));
        when(teacherRepository.findByLanguages_Id(languageId)).thenReturn(Collections.emptyList());

        // Act
        List<Teacher> result = languageService.getExpertsByLanguage(languageId);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        
        verify(languageRepository).findById(languageId);
        verify(teacherRepository).findByLanguages_Id(languageId);
    }

    @Test
    @DisplayName("Should throw exception when language not found")
    void getExpertsByLanguage_InvalidLanguageId_ShouldThrowException() {
        // Arrange
        Long invalidLanguageId = 999L;
        
        when(languageRepository.findById(invalidLanguageId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> 
            languageService.getExpertsByLanguage(invalidLanguageId));
        
        verify(languageRepository).findById(invalidLanguageId);
        verify(teacherRepository, never()).findByLanguages_Id(any());
    }

    @Test
    @DisplayName("Should list multiple experts for popular language")
    void getExpertsByLanguage_PopularLanguage_ShouldReturnMultipleExperts() {
        // Arrange
        Long languageId = 1L;
        List<Teacher> multipleExperts = Arrays.asList(mockTeacher1, mockTeacher2, mockTeacher3);
        
        when(languageRepository.findById(languageId)).thenReturn(Optional.of(mockJavaLanguage));
        when(teacherRepository.findByLanguages_Id(languageId)).thenReturn(multipleExperts);

        // Act
        List<Teacher> result = languageService.getExpertsByLanguage(languageId);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(multipleExperts));
        
        verify(languageRepository).findById(languageId);
        verify(teacherRepository).findByLanguages_Id(languageId);
    }

    // ================ Student Listing by Level Tests ================

    @Test
    @DisplayName("Should list students by Beginner experience level")
    void getStudentsByExperienceLevel_BeginnerLevel_ShouldReturnBeginnerStudents() {
        // Arrange
        ExperienceLevel level = ExperienceLevel.Beginner;
        List<Student> beginnerStudents = Arrays.asList(mockBeginnerStudent1, mockBeginnerStudent2);
        
        when(studentRepository.findByExperienceLevel(level)).thenReturn(beginnerStudents);

        // Act
        List<Student> result = languageService.getStudentsByExperienceLevel(level);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(mockBeginnerStudent1));
        assertTrue(result.contains(mockBeginnerStudent2));
        result.forEach(student -> 
            assertEquals(ExperienceLevel.Beginner, student.getExperienceLevel()));
        
        verify(studentRepository).findByExperienceLevel(level);
    }

    @Test
    @DisplayName("Should list students by Junior experience level")
    void getStudentsByExperienceLevel_JuniorLevel_ShouldReturnJuniorStudents() {
        // Arrange
        ExperienceLevel level = ExperienceLevel.Junior;
        List<Student> juniorStudents = Arrays.asList(mockJuniorStudent1);
        
        when(studentRepository.findByExperienceLevel(level)).thenReturn(juniorStudents);

        // Act
        List<Student> result = languageService.getStudentsByExperienceLevel(level);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(mockJuniorStudent1));
        assertEquals(ExperienceLevel.Junior, result.get(0).getExperienceLevel());
        
        verify(studentRepository).findByExperienceLevel(level);
    }

    @Test
    @DisplayName("Should list students by Senior experience level")
    void getStudentsByExperienceLevel_SeniorLevel_ShouldReturnSeniorStudents() {
        // Arrange
        ExperienceLevel level = ExperienceLevel.Senior;
        List<Student> seniorStudents = Arrays.asList(mockSeniorStudent1);
        
        when(studentRepository.findByExperienceLevel(level)).thenReturn(seniorStudents);

        // Act
        List<Student> result = languageService.getStudentsByExperienceLevel(level);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(mockSeniorStudent1));
        assertEquals(ExperienceLevel.Senior, result.get(0).getExperienceLevel());
        
        verify(studentRepository).findByExperienceLevel(level);
    }

    @Test
    @DisplayName("Should list students by Master experience level")
    void getStudentsByExperienceLevel_MasterLevel_ShouldReturnMasterStudents() {
        // Arrange
        ExperienceLevel level = ExperienceLevel.Master;
        List<Student> masterStudents = Arrays.asList(mockMasterStudent1);
        
        when(studentRepository.findByExperienceLevel(level)).thenReturn(masterStudents);

        // Act
        List<Student> result = languageService.getStudentsByExperienceLevel(level);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(mockMasterStudent1));
        assertEquals(ExperienceLevel.Master, result.get(0).getExperienceLevel());
        
        verify(studentRepository).findByExperienceLevel(level);
    }

    @Test
    @DisplayName("Should return empty list when no students at specific level")
    void getStudentsByExperienceLevel_NoStudentsAtLevel_ShouldReturnEmptyList() {
        // Arrange
        ExperienceLevel level = ExperienceLevel.Master;
        
        when(studentRepository.findByExperienceLevel(level)).thenReturn(Collections.emptyList());

        // Act
        List<Student> result = languageService.getStudentsByExperienceLevel(level);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        
        verify(studentRepository).findByExperienceLevel(level);
    }

    // ================ Comprehensive Scenario Tests ================

    @Test
    @DisplayName("Should validate all experience levels return correct student counts")
    void validateAllExperienceLevels_ShouldReturnCorrectCounts() {
        // Arrange
        when(studentRepository.findByExperienceLevel(ExperienceLevel.Beginner))
            .thenReturn(Arrays.asList(mockBeginnerStudent1, mockBeginnerStudent2));
        when(studentRepository.findByExperienceLevel(ExperienceLevel.Junior))
            .thenReturn(Arrays.asList(mockJuniorStudent1));
        when(studentRepository.findByExperienceLevel(ExperienceLevel.Senior))
            .thenReturn(Arrays.asList(mockSeniorStudent1));
        when(studentRepository.findByExperienceLevel(ExperienceLevel.Master))
            .thenReturn(Arrays.asList(mockMasterStudent1));

        // Act & Assert for each level
        List<Student> beginners = languageService.getStudentsByExperienceLevel(ExperienceLevel.Beginner);
        assertEquals(2, beginners.size());
        
        List<Student> juniors = languageService.getStudentsByExperienceLevel(ExperienceLevel.Junior);
        assertEquals(1, juniors.size());
        
        List<Student> seniors = languageService.getStudentsByExperienceLevel(ExperienceLevel.Senior);
        assertEquals(1, seniors.size());
        
        List<Student> masters = languageService.getStudentsByExperienceLevel(ExperienceLevel.Master);
        assertEquals(1, masters.size());

        // Verify all repository calls were made
        verify(studentRepository, times(1)).findByExperienceLevel(ExperienceLevel.Beginner);
        verify(studentRepository, times(1)).findByExperienceLevel(ExperienceLevel.Junior);
        verify(studentRepository, times(1)).findByExperienceLevel(ExperienceLevel.Senior);
        verify(studentRepository, times(1)).findByExperienceLevel(ExperienceLevel.Master);
    }

    @Test
    @DisplayName("Should validate multiple languages have different expert counts")
    void validateMultipleLanguages_ShouldHaveDifferentExpertCounts() {
        // Arrange
        Long javaLanguageId = 1L;
        Long pythonLanguageId = 2L;
        
        when(languageRepository.findById(javaLanguageId)).thenReturn(Optional.of(mockJavaLanguage));
        when(languageRepository.findById(pythonLanguageId)).thenReturn(Optional.of(mockPythonLanguage));
        
        when(teacherRepository.findByLanguages_Id(javaLanguageId))
            .thenReturn(Arrays.asList(mockTeacher1, mockTeacher2, mockTeacher3));
        when(teacherRepository.findByLanguages_Id(pythonLanguageId))
            .thenReturn(Arrays.asList(mockTeacher1));

        // Act
        List<Teacher> javaExperts = languageService.getExpertsByLanguage(javaLanguageId);
        List<Teacher> pythonExperts = languageService.getExpertsByLanguage(pythonLanguageId);

        // Assert
        assertEquals(3, javaExperts.size());
        assertEquals(1, pythonExperts.size());
        assertNotEquals(javaExperts.size(), pythonExperts.size());
        
        verify(languageRepository).findById(javaLanguageId);
        verify(languageRepository).findById(pythonLanguageId);
        verify(teacherRepository).findByLanguages_Id(javaLanguageId);
        verify(teacherRepository).findByLanguages_Id(pythonLanguageId);
    }

    @Test
    @DisplayName("Should handle edge case with null experience level")
    void getStudentsByExperienceLevel_NullLevel_ShouldHandleGracefully() {
        // Arrange
        ExperienceLevel level = null;
        
        when(studentRepository.findByExperienceLevel(level)).thenReturn(Collections.emptyList());

        // Act
        List<Student> result = languageService.getStudentsByExperienceLevel(level);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        
        verify(studentRepository).findByExperienceLevel(level);
    }

    // ================ Integration Scenario Tests ================

    @Test
    @DisplayName("Should validate complete workflow: experts and students for different scenarios")
    void validateCompleteWorkflow_ExpertsAndStudents_ShouldWorkCorrectly() {
        // Arrange - Multiple languages and levels
        Long languageId = 1L;
        
        when(languageRepository.findById(languageId)).thenReturn(Optional.of(mockJavaLanguage));
        when(teacherRepository.findByLanguages_Id(languageId))
            .thenReturn(Arrays.asList(mockTeacher1, mockTeacher2));
        
        when(studentRepository.findByExperienceLevel(ExperienceLevel.Beginner))
            .thenReturn(Arrays.asList(mockBeginnerStudent1, mockBeginnerStudent2));
        when(studentRepository.findByExperienceLevel(ExperienceLevel.Junior))
            .thenReturn(Arrays.asList(mockJuniorStudent1));

        // Act - Get experts and students
        List<Teacher> experts = languageService.getExpertsByLanguage(languageId);
        List<Student> beginners = languageService.getStudentsByExperienceLevel(ExperienceLevel.Beginner);
        List<Student> juniors = languageService.getStudentsByExperienceLevel(ExperienceLevel.Junior);

        // Assert - Validate results
        assertNotNull(experts);
        assertNotNull(beginners);
        assertNotNull(juniors);
        
        assertEquals(2, experts.size());
        assertEquals(2, beginners.size());
        assertEquals(1, juniors.size());
        
        // Verify expert-student ratio for this scenario
        assertTrue(experts.size() == beginners.size()); // 2 experts for 2 beginners
        assertTrue(experts.size() > juniors.size());    // 2 experts for 1 junior
        
        // Verify all repository interactions
        verify(languageRepository).findById(languageId);
        verify(teacherRepository).findByLanguages_Id(languageId);
        verify(studentRepository).findByExperienceLevel(ExperienceLevel.Beginner);
        verify(studentRepository).findByExperienceLevel(ExperienceLevel.Junior);
    }
}