package com.YronJack.IronWars.service.interfaces;

import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> getStudentById(Long id);
    Student createStudent(Student studentRequest);
    Student updateStudent(Long id, Exam studentUpdate);
    void deleteStudent(Long id);
    List<Student> getStudentByStudentId(Long studentId);
}
