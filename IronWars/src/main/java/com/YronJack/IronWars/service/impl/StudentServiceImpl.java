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
    public StudentResponseDTO createStudent(Student studentRequest) {
        Student student = new Student();
        student.setNickName(studentRequest.getNickName());
        student.setAverageScore(Score.Null);
        student.setExperienceLevel(0L);
        // No asignar examList, quedará vacío o null

        Student savedStudent = studentRepository.save(student);
        return mapToResponseDTO(savedStudent);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, Student studentUpdate) {
        Optional<Student> optionalStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + id));
        if (optionalStudent.isEmpty()) {
            // Maneja el caso de no encontrado, lanza excepción o retorna null
            return null;
        }
        Student student = optionalStudent.get();
        student.setNickName(studentUpdate.getNickName());
        student.setAverageScore(studentUpdate.getAverageScore());
        student.setExperienceLevel(studentUpdate.getExperienceLevel());
        student.setExamList();

        Student updatedStudent = studentRepository.save(student);
        return mapToResponseDTO(updatedStudent);
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
