
package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.student.StudentMinimalResponseDTO;
import com.YronJack.IronWars.dto.student.StudentRequestDTO;
import com.YronJack.IronWars.dto.student.StudentResponseDTO;
import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.service.impl.StudentServiceImpl;
import com.YronJack.IronWars.service.interfaces.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

   @GetMapping("/{id}")
   public ResponseEntity<StudentMinimalResponseDTO> getStudentById(@PathVariable Long id) {
       StudentMinimalResponseDTO student = studentService.getStudentById(id);
       if (student != null) {
           return ResponseEntity.ok(student);
       } else {
           return ResponseEntity.notFound().build();
       }
   }


    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getStudentAllStudents();
    }

    @GetMapping("/students/list_of_Exams")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getStudentAllStudents();
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody Student student) {
        StudentResponseDTO created = studentService.createStudent(student);
        return ResponseEntity.ok(created);
    }


    @GetMapping("/student/list_of_Exams/{student_Id}")
    public List<Exam> getExamsByStudent(@PathVariable Long studentId){
        return studentService.getAllExamsByStudentId(studentId);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<StudentResponseDTO> deleteStudentById(@PathVariable Long Id){
       studentService.deleteStudent(Id);
       return ResponseEntity.ok().build();
    }

    @PatchMapping("/{Id}")
    public ResponseEntity<StudentResponseDTO> updateStudentById(@PathVariable Long Id, @Valid @RequestBody Student student) {
       studentService.updateStudent(Id, student);
       return ResponseEntity.ok().build();
    }
}