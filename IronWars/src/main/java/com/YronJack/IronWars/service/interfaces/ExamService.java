package com.YronJack.IronWars.service.interfaces;

import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Exercise;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ExamService {
    Optional<Exam> getExamById(Long id);
    Exam createExam(Exam examRequest);
    Optional<Exam> updateExam(Long id, Exam examUpdate);
    Boolean deleteExam(Long id);
    
    // Methods for managing N:M relationship with exercises
    Optional<Exam> addExerciseToExam(Long examId, Long exerciseId);
    Optional<Exam> removeExerciseFromExam(Long examId, Long exerciseId);
    List<Exercise> getExercisesByExamId(Long examId);

}
