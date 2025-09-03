package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.service.impl.ExamServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exams")
@Tag(name = "Exams", description = "Operations related to exams")
public class ExamController {
    @Autowired
    private ExamServiceImpl examService;

    @Operation(summary = "Create a new exam")
    @ApiResponse(responseCode = "200", description = "Exam created successfully")
    @PostMapping
    public Exam createExam(@RequestBody Exam examRequest) {
        return examService.createExam(examRequest);
    }

    @Operation(summary = "Get all exams")
    @ApiResponse(responseCode = "200", description = "List of exams")
    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @Operation(summary = "Get exam by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exam found"),
            @ApiResponse(responseCode = "404", description = "Exam not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(
            @Parameter(description = "ID of the exam to retrieve") @PathVariable Long id) {
        Optional<Exam> exam = examService.getExamById(id);
        return exam.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update exam by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exam updated"),
            @ApiResponse(responseCode = "404", description = "Exam not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Exam> UpdateExamById(
            @Parameter(description = "ID of the exam to update") @PathVariable Long id,
            @RequestBody Exam exam) {
        Optional<Exam> updateExam = examService.updateExam(id, exam);
        return updateExam.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete exam by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Exam deleted"),
            @ApiResponse(responseCode = "404", description = "Exam not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamById(
            @Parameter(description = "ID of the exam to delete") @PathVariable Long id) {
        if (examService.deleteExam(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}