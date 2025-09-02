package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.dto.student.StudentMinimalResponseDTO;
import com.YronJack.IronWars.dto.student.StudentRequestDTO;
import com.YronJack.IronWars.dto.student.StudentResponseDTO;
import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.repository.ExamRepository;
import com.YronJack.IronWars.repository.StudentRepository;
import com.YronJack.IronWars.service.interfaces.StudentService;
import com.YronJack.IronWars.unums.Score;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    public StudentServiceImpl(StudentRepository studentRepository, ExamRepository examRepository){
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    @Override
    public Optional<StudentMinimalResponseDTO> getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(this::mapToMinimalResponseDTO);
    }


    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequest) {
        return studentRepository.save(studentRequest);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, Exam studentUpdate) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public List<StudentResponseDTO> getStudentAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private StudentResponseDTO mapToResponseDTO(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setStudentId(student.getId());
        dto.setNickName(student.getNickName());
        // Suponiendo que Student hereda de User y User tiene un campo PersonalData
        if (student.getPersonalData() != null) {
            dto.setName(student.getPersonalData().getName());
            dto.setLastName(student.getPersonalData().getLastName());
            dto.setAddress(student.getPersonalData().getAddress());
        }
        dto.setAverageScore(student.getAverageScore());
        dto.setExperienceLevel(student.getExperienceLevel());
        dto.setExamList(student.getExamList());
        return dto;
    }

    private StudentMinimalResponseDTO mapToMinimalResponseDTO(Student student) {
        StudentMinimalResponseDTO dto = new StudentMinimalResponseDTO();
        dto.setStudentId(student.getId());
        dto.setNickName(student.getNickName());
        dto.setAverageScore(student.getAverageScore());
        dto.setExperienceLevel(student.getExperienceLevel());
        dto.setExamList(student.getExamList());
        return dto;
    }
}
