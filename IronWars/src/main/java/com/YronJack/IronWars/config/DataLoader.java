package com.YronJack.IronWars.config;

import com.YronJack.IronWars.model.*;
import com.YronJack.IronWars.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    public void run(String... args) {

        if( examRepository.count() > 0 ||
                languageRepository.count() > 0 ||
                studentRepository.count() > 0 ||
                teacherRepository.count() > 0) {
            return;
        }
        // Exercises
        Exercise exercise1 = new Exercise();
        Exercise exercise2 = new Exercise();

        // Language
        Language language1 = new Language();
        Language language2 = new Language();

        // Exams
        Exam exam1 = new Exam();
        Exam exam2 = new Exam();

        // Student
        Student student1 = new Student();
        Student student2 = new Student();

        // Teacher
        Teacher teacher1 = new Teacher();
        Teacher teacher2 = new Teacher();

    }
}
