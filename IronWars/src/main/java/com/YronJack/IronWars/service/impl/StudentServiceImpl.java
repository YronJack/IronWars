package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.repository.ExamRepository;
import com.YronJack.IronWars.repository.StudentRepository;
import com.YronJack.IronWars.service.interfaces.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    public StudentServiceImpl(StudentRepository studentRepository, ExamRepository examRepository){
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student createStudent(Student studentRequest) {
        return studentRepository.create(studentRequest);
    }

    @Override
    public Student updateStudent(Long id, Exam studentUpdate) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public List<Student> getStudentByStudentId(Long studentId) {
        return List.of();
    }
}
