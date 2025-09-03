package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.student.StudentMinimalResponseDTO;
import com.YronJack.IronWars.dto.student.StudentResponseDTO;
import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    // --- GET ---
    @GetMapping("/{id}")
    public ResponseEntity<StudentMinimalResponseDTO> getStudentById(@PathVariable Long id) {
        StudentMinimalResponseDTO student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/complete_list")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getStudentAllStudents();
    }

    @GetMapping
    public List<StudentMinimalResponseDTO> getAllStudentsMinimalResponse() {
        return studentService.getAllStudentsMinimal();
    }

    @GetMapping("/student/list_of_Exams/{student_Id}")
    public List<Exam> getExamsByStudentId(@PathVariable Long student_Id){
        return studentService.getAllExamsByStudentId(student_Id);
    }

    // --- POST ---
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody Student student) {
        StudentResponseDTO created = studentService.createStudent(student);
        return ResponseEntity.ok(created);
    }

    // --- PATCH ---
    @PatchMapping("/{Id}")
    public ResponseEntity<StudentResponseDTO> updateStudentById(@PathVariable Long Id, @Valid @RequestBody Student student) {
        studentService.updateStudent(Id, student);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<StudentResponseDTO> addExamToStudent(
            @PathVariable Long studentId,
            @PathVariable Long examId) {
        StudentResponseDTO response = studentService.addExamToStudent(studentId, examId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<Void> removeExamFromStudent(
            @PathVariable Long studentId,
            @PathVariable Long examId) {
        studentService.removeExamFromStudent(studentId, examId);
        return ResponseEntity.noContent().build();
    }

    // --- DELETE ---
    @DeleteMapping("/{Id}")
    public ResponseEntity<StudentResponseDTO> deleteStudentById(@PathVariable Long Id){
        studentService.deleteStudent(Id);
        return ResponseEntity.ok().build();
    }
}