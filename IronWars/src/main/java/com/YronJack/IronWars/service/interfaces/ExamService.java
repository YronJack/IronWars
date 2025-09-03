package com.YronJack.IronWars.service.interfaces;

import com.YronJack.IronWars.model.Exam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ExamService {
    Optional<Exam> getExamById(Long id);
    Exam createExam(Exam examRequest);
    Optional<Exam> updateExam(Long id, Exam examUpdate);
    Boolean deleteExam(Long id);

}
