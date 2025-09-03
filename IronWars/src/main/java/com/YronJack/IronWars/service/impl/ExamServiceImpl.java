package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Exercise;
import com.YronJack.IronWars.repository.ExamRepository;
import com.YronJack.IronWars.repository.ExerciseRepository;
import com.YronJack.IronWars.service.interfaces.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;
    
    @Autowired
    private ExerciseRepository exerciseRepository;


    @Override
    public Optional<Exam> getExamById(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        return exam;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam createExam(Exam examRequest) {
        return examRepository.save(examRequest);

    }

    public Optional<Exam> updateExam(Long id, Exam exam) {
        if (examRepository.existsById(id)) {
            exam.setExam_id(id);
            return Optional.of(examRepository.save(exam));
        }
        return Optional.empty();
    }

    public Boolean deleteExam(Long id) {
        if (examRepository.existsById(id)) {
            examRepository.deleteById(id);
            System.out.println("Exam Deleted Successfully");
            return true;
        }
        return false;
    }
    
    @Override
    public Optional<Exam> addExerciseToExam(Long examId, Long exerciseId) {
        Optional<Exam> examOptional = examRepository.findById(examId);
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        
        if (examOptional.isPresent() && exerciseOptional.isPresent()) {
            Exam exam = examOptional.get();
            Exercise exercise = exerciseOptional.get();
            
            // Initialize exercises list if null
            if (exam.getExercises() == null) {
                exam.setExercises(new java.util.ArrayList<>());
            }
            
            // Add exercise only if not already present
            if (!exam.getExercises().contains(exercise)) {
                exam.getExercises().add(exercise);
                return Optional.of(examRepository.save(exam));
            }
            return Optional.of(exam);
        }
        return Optional.empty();
    }
    
    @Override
    public Optional<Exam> removeExerciseFromExam(Long examId, Long exerciseId) {
        Optional<Exam> examOptional = examRepository.findById(examId);
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        
        if (examOptional.isPresent() && exerciseOptional.isPresent()) {
            Exam exam = examOptional.get();
            Exercise exercise = exerciseOptional.get();
            
            if (exam.getExercises() != null && exam.getExercises().contains(exercise)) {
                exam.getExercises().remove(exercise);
                return Optional.of(examRepository.save(exam));
            }
            return Optional.of(exam);
        }
        return Optional.empty();
    }
    
    @Override
    public List<Exercise> getExercisesByExamId(Long examId) {
        Optional<Exam> examOptional = examRepository.findById(examId);
        if (examOptional.isPresent()) {
            Exam exam = examOptional.get();
            return exam.getExercises() != null ? exam.getExercises() : new java.util.ArrayList<>();
        }
        return new java.util.ArrayList<>();
    }
}
