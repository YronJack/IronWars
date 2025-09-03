package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.student.StudentMinimalResponseDTO;
import com.YronJack.IronWars.dto.student.StudentResponseDTO;
import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.service.impl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Students", description = "Operations related to students")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    // --- GET ---
    @Operation(summary = "Get student by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentMinimalResponseDTO> getStudentById(
            @Parameter(description = "ID of the student") @PathVariable Long id) {
        StudentMinimalResponseDTO student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get complete list of students (detailed response)")
    @ApiResponse(responseCode = "200", description = "List of students")
    @GetMapping("/complete_list")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getStudentAllStudents();
    }

    @Operation(summary = "Get list of students (minimal response)")
    @ApiResponse(responseCode = "200", description = "Minimal list of students")
    @GetMapping
    public List<StudentMinimalResponseDTO> getAllStudentsMinimalResponse() {
        return studentService.getAllStudentsMinimal();
    }

    @Operation(summary = "Get exams by student ID")
    @ApiResponse(responseCode = "200", description = "List of exams for the student")
    @GetMapping("/student/list_of_Exams/{student_Id}")
    public List<Exam> getExamsByStudentId(
            @Parameter(description = "ID of the student") @PathVariable Long student_Id){
        return studentService.getAllExamsByStudentId(student_Id);
    }

    // --- POST ---
    @Operation(summary = "Create a new student")
    @ApiResponse(responseCode = "200", description = "Student created successfully")
    @PostMapping
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody Student student) {
        Student created = studentService.createStudent(student);
        return ResponseEntity.ok(created);
    }

    // --- PATCH ---
    @Operation(summary = "Update student by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Student updated"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PatchMapping("/{Id}")
    public ResponseEntity<StudentResponseDTO> updateStudentById(
            @Parameter(description = "ID of the student to update") @PathVariable Long Id,
            @Valid @RequestBody Student student) {
        studentService.updateStudent(Id, student);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Add exam to student")
    @ApiResponse(responseCode = "200", description = "Exam added to student")
    @PatchMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<StudentResponseDTO> addExamToStudent(
            @Parameter(description = "ID of the student") @PathVariable Long studentId,
            @Parameter(description = "ID of the exam") @PathVariable Long examId) {
        StudentResponseDTO response = studentService.addExamToStudent(studentId, examId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Remove exam from student")
    @ApiResponse(responseCode = "204", description = "Exam removed from student")
    @DeleteMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<Void> removeExamFromStudent(
            @Parameter(description = "ID of the student") @PathVariable Long studentId,
            @Parameter(description = "ID of the exam") @PathVariable Long examId) {
        studentService.removeExamFromStudent(studentId, examId);
        return ResponseEntity.noContent().build();
    }

    // --- DELETE ---
    @Operation(summary = "Delete student by ID")
    @ApiResponse(responseCode = "200", description = "Student deleted")
    @DeleteMapping("/{Id}")
    public ResponseEntity<StudentResponseDTO> deleteStudentById(
            @Parameter(description = "ID of the student to delete") @PathVariable Long Id){
        studentService.deleteStudent(Id);
        return ResponseEntity.ok().build();
    }
}