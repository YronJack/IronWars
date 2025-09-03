package com.YronJack.IronWars.config;

import com.YronJack.IronWars.enums.Dificulty;
import com.YronJack.IronWars.enums.ExperienceLevel;
import com.YronJack.IronWars.enums.Score;
import com.YronJack.IronWars.model.*;
import com.YronJack.IronWars.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    public void run(String... args) {
        // TODO: DataLoader temporarily disabled due to validation constraint issues
        // Uncomment and fix validation constraints for sample data when using with proper database setup
        System.out.println("DataLoader: Skipping sample data loading (validation constraints need to be addressed)");
        return;
        
        /* // Prevent data duplication on restart
        if (examRepository.count() > 0 ||
                languageRepository.count() > 0 ||
                studentRepository.count() > 0 ||
                teacherRepository.count() > 0) {
            return;
        }

        // Sample data creation code follows but is commented out due to validation constraints
        // ... (rest of the original data loading code would be here)
        */
    }
}
