package com.YronJack.IronWars.service.interfaces;

import com.YronJack.IronWars.dto.student.StudentMinimalResponseDTO;
import com.YronJack.IronWars.dto.student.StudentResponseDTO;
import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentMinimalResponseDTO getStudentById(Long id);
    StudentResponseDTO createStudent(Student studentRequest);
    StudentResponseDTO updateStudent(Long id, Student studentUpdate);
    void deleteStudent(Long id);
    List<StudentResponseDTO> getStudentAllStudents();
    List<Exam> getAllExamsByStudentId(Long studentId);
//    Exam addExamToStudent(Long studentId, Exam exam);

    List<StudentMinimalResponseDTO> getAllStudentsMinimal();

    StudentResponseDTO addExamToStudent(Long studentId, Long examId);

    void removeExamFromStudent(Long studentId, Long examId);
    Exam getExamById(Long studentId, Long examId);
}
