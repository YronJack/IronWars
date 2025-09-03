package com.YronJack.IronWars.config;

import com.YronJack.IronWars.enums.Difficulty;
import com.YronJack.IronWars.enums.ExperienceLevel;
import com.YronJack.IronWars.enums.Score;
import com.YronJack.IronWars.model.*;
import com.YronJack.IronWars.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ExerciseRepository exerciseRepository;
    private final ExamRepository examRepository;
    private final LanguageRepository languageRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    public DataLoader( ExerciseRepository exerciseRepository,
                       ExamRepository examRepository,
                       LanguageRepository languageRepository,
                       StudentRepository studentRepository,
                       TeacherRepository teacherRepository) {
        this.exerciseRepository = exerciseRepository;
        this.examRepository = examRepository;
        this.languageRepository = languageRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Prevent data duplication on restart
        if (examRepository.count() > 0 ||
                exerciseRepository.count() > 0 ||
                languageRepository.count() > 0 ||
                studentRepository.count() > 0 ||
                teacherRepository.count() > 0) {
            return;
        }

            // --- Languages ---
            Language language1 = new Language();
            language1.setName("Java");
            language1.setDomain("Backend");

            Language language2 = new Language();
            language2.setName("JavaScript");
            language2.setDomain("Frontend");

            languageRepository.save(language1);
            languageRepository.save(language2);

            // --- Teachers ---
            Teacher teacher1 = new Teacher();
            teacher1.setNickName("profAlice");
            teacher1.setPassword("alicePass123");
            teacher1.setYearsExperience(12);
            teacher1.setRating(4.9);
            teacher1.setPersonalData(new PersonalData(
                    "Alice", "Smith", "123 Main St", "alice.smith@email.com", "555-111-222"
            ));

            Teacher teacher2 = new Teacher();
            teacher2.setNickName("profBob");
            teacher2.setPassword("bobPass123");
            teacher2.setYearsExperience(8);
            teacher2.setRating(4.6);
            teacher2.setPersonalData(new PersonalData(
                    "Bob", "Johnson", "456 Oak Ave", "bob.johnson@email.com", "555-333-444"
            ));

            teacherRepository.save(teacher1);
            teacherRepository.save(teacher2);

            // --- Link teachers to languages ---
            language1.setTeachers(new ArrayList<>(List.of(teacher1)));
            language2.setTeachers(new ArrayList<>(List.of(teacher2)));
            languageRepository.save(language1);
            languageRepository.save(language2);

            teacher1.setLanguages(new ArrayList<>(List.of(language1)));
            teacher2.setLanguages(new ArrayList<>(List.of(language2)));
            teacherRepository.save(teacher1);
            teacherRepository.save(teacher2);

            // --- Exercises ---
            Exercise exercise1 = new Exercise();
            exercise1.setTitle("Java Variables");
            exercise1.setDescription("Declare and initialize a variable in Java.");
            exercise1.setStarterCode("int number = ;");
            exercise1.setSolution("int number = 10;");
            exercise1.setDifficulty(Difficulty.Beginner);
            exercise1.setLanguage(language1);

            Exercise exercise2 = new Exercise();
            exercise2.setTitle("JS Functions");
            exercise2.setDescription("Write a function that returns the sum of two numbers.");
            exercise2.setStarterCode("function sum(a, b) { }");
            exercise2.setSolution("function sum(a, b) { return a + b; }");
            exercise2.setDifficulty(Difficulty.Beginner);
            exercise2.setLanguage(language2);

            exerciseRepository.save(exercise1);
            exerciseRepository.save(exercise2);

            // --- Exams ---
            Exam exam1 = new Exam();
            /* exam1.setTitle("Java Basic Exam"); */
            exam1.setLanguage(language1);
            /* exam1.setTeacher(teacher1);*/
            exam1.setDuration(20);
            exam1.setExercises(new ArrayList<>(List.of(exercise1)));

            Exam exam2 = new Exam();
            /* exam2.setTitle("JS Basic Exam");*/
            exam2.setLanguage(language2);
            exam2.setDuration(20);

            /*exam2.setTeacher(teacher2);*/
            exam2.setExercises(new ArrayList<>(List.of(exercise2)));

            examRepository.save(exam1);
            examRepository.save(exam2);

            // --- Students ---
            Student student1 = new Student();
            student1.setNickName("johnDoe");
            student1.setPassword("johnPass123");
            student1.setPersonalData(new PersonalData(
                    "John", "Doe", "789 Pine Rd", "john.doe@email.com", "555-555-666"
            ));
            student1.setAverageScore(Score.Satisfactory);
            student1.setExperienceLevel(ExperienceLevel.Beginner);
            student1.setExamList(new ArrayList<>(List.of(exam1)));


            Student student2 = new Student();
            student2.setNickName("janeRoe");
            student2.setPassword("janePass123");
            student2.setPersonalData(new PersonalData(
                    "Jane", "Roe", "321 Maple Ln", "jane.roe@email.com", "555-777-888"
            ));
            student2.setAverageScore(Score.Fail);
            student2.setExperienceLevel(ExperienceLevel.Beginner);
            student2.setExamList(new ArrayList<>(List.of(exam2)));


            studentRepository.save(student1);
            studentRepository.save(student2);
            // Persist all entities
            teacherRepository.saveAll(List.of(teacher1, teacher2));
            languageRepository.saveAll(List.of(language1, language2));
            exerciseRepository.saveAll(List.of(exercise1, exercise2));
            examRepository.saveAll(List.of(exam1, exam2));
            studentRepository.saveAll(List.of(student1, student2));
        }
}
