package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.repository.ExamRepository;
import com.YronJack.IronWars.service.interfaces.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;


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
}
