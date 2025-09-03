<<<<<<< Updated upstream
package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.student.StudentMinimalResponseDTO;
import com.YronJack.IronWars.dto.student.StudentRequestDTO;
import com.YronJack.IronWars.dto.student.StudentResponseDTO;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.service.interfaces.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentMinimalResponseDTO> getStudentById(@PathVariable Long id) {
        Optional<StudentMinimalResponseDTO> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getStudentAllStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED);
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody Student student) {
        StudentResponseDTO created = studentService.createStudent(student);
        return ResponseEntity.ok(created);
    }


    // Métodos update y delete no implementados en el servicio, por lo que se omiten aquí.
}