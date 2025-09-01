package com.YronJack.IronWars.service.interfaces;

import com.YronJack.IronWars.model.Exam;

import java.util.List;

public interface ExamService {
    Exam getExamById(Long id);
    Exam createExam(Exam examRequest);
    Exam updateExam(Long id, Exam examUpdate);
    void deleteExam(Long id);
    List<Exam> getExamsByUsuarioId(Long usuarioId);
}
