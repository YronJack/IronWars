package com.YronJack.IronWars.controller;


import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.service.impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamServiceImpl examService;

    @PostMapping
    public Exam createExam(@RequestBody Exam examRequest) {
        return examService.createExam(examRequest);
    }

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id){
        Optional<Exam> exam = examService.getExamById(id);
        return exam.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> UpdateExamById(@PathVariable Long id, @RequestBody Exam exam){
        Optional<Exam> updateExam= examService.updateExam(id,exam);
        return updateExam.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamById(@PathVariable Long id){
        if(examService.deleteExam(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }





}
