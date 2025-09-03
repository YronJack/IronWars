package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.util.customException.ResourceNotFoundException;
import com.YronJack.IronWars.dto.student.StudentMinimalResponseDTO;
import com.YronJack.IronWars.dto.student.StudentResponseDTO;
import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.repository.ExamRepository;
import com.YronJack.IronWars.repository.StudentRepository;
import com.YronJack.IronWars.service.interfaces.StudentService;
import com.YronJack.IronWars.enums.Score;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    public StudentServiceImpl(StudentRepository studentRepository, ExamRepository examRepository) {
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    // --- CRUD Estudiante ---

    @Override
    public StudentResponseDTO createStudent(Student studentRequest) {
        Student student = new Student();
        student.setNickName(studentRequest.getNickName());
        student.setAverageScore(Score.Null);
        student.setExperienceLevel(0L);
        Student savedStudent = studentRepository.save(student);
        return mapToResponseDTO(savedStudent);
    }

    @Override
    public StudentMinimalResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + id));
        return mapToMinimalResponseDTO(student);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, Student studentUpdate) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + id));
        student.setNickName(studentUpdate.getNickName());
        student.setAverageScore(studentUpdate.getAverageScore());
        student.setExperienceLevel(studentUpdate.getExperienceLevel());

        if (student.getPersonalData() != null && studentUpdate.getPersonalData() != null) {
            if (studentUpdate.getPersonalData().getName() != null) {
                student.getPersonalData().setName(studentUpdate.getPersonalData().getName());
            }
            if (studentUpdate.getPersonalData().getLastName() != null) {
                student.getPersonalData().setLastName(studentUpdate.getPersonalData().getLastName());
            }
            if (studentUpdate.getPersonalData().getAddress() != null) {
                student.getPersonalData().setAddress(studentUpdate.getPersonalData().getAddress());
            }
            if (studentUpdate.getPersonalData().getEmail() != null) {
                student.getPersonalData().setEmail(studentUpdate.getPersonalData().getEmail());
            }
            if (studentUpdate.getPersonalData().getPhone() != null) {
                student.getPersonalData().setPhone(studentUpdate.getPersonalData().getPhone());
            }
        }

        student.setExamList(studentUpdate.getExamList());
        Student updatedStudent = studentRepository.save(student);
        return mapToResponseDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Estudiante no encontrado con id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResponseDTO> getStudentAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentMinimalResponseDTO> getAllStudentsMinimal() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToMinimalResponseDTO)
                .collect(Collectors.toList());
    }

    // --- Gestión de Exámenes ---

    @Override
    public StudentResponseDTO addExamToStudent(Long studentId, Long examId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + studentId));
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Examen no encontrado con id: " + examId));
        List<Exam> exams = student.getExamList();
        if (exams.stream().anyMatch(e -> e.getExam_id().equals(examId))) {
            throw new IllegalArgumentException("El examen ya está asociado al estudiante");
        }
        exams.add(exam);
        student.setExamList(exams);
        Student updatedStudent = studentRepository.save(student);
        return mapToResponseDTO(updatedStudent);
    }

    @Override
    public void removeExamFromStudent(Long studentId, Long examId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + studentId));
        List<Exam> exams = student.getExamList();
        boolean removed = exams.removeIf(exam -> exam.getExam_id().equals(examId));
        if (!removed) {
            throw new ResourceNotFoundException("Examen no encontrado con id: " + examId + " para el estudiante");
        }
        student.setExamList(exams);
        studentRepository.save(student);
    }



    @Override
    public List<Exam> getAllExamsByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + studentId));
        return student.getExamList();
    }



    // --- Métodos utilitarios privados ---

    private StudentResponseDTO mapToResponseDTO(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setStudentId(student.getId());
        dto.setNickName(student.getNickName());
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