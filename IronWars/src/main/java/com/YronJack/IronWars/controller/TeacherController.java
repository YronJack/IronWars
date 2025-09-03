package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.teacher.TeacherRequestDTO;
import com.YronJack.IronWars.dto.teacher.TeacherResponseDTO;
import com.YronJack.IronWars.model.Teacher;
import com.YronJack.IronWars.service.interfaces.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //CRUD

    @GetMapping
    public List<TeacherResponseDTO> getAll() {
        return teacherService.getAll()
                .stream()
                .map(TeacherResponseDTO::fromEntity)
                .toList();
    }
    @GetMapping("/{id}")
    public TeacherResponseDTO getById(@PathVariable Long id) {
        return TeacherResponseDTO.fromEntity(teacherService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponseDTO create(@Valid @RequestBody TeacherRequestDTO request) {
        Teacher saved = teacherService.create(request.toEntity());
        return TeacherResponseDTO.fromEntity(saved);
    }

    @PutMapping("/{id}")
    public TeacherResponseDTO update(@PathVariable Long id, @Valid @RequestBody TeacherRequestDTO request) {
        Teacher updated = teacherService.update(id, request.toEntity());
        return TeacherResponseDTO.fromEntity(updated);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }
//---

    @PostMapping("/{teacherId}/languages/{languageId}")
    public TeacherResponseDTO addLanguage(@PathVariable Long teacherId, @PathVariable Long languageId) {
        return TeacherResponseDTO.fromEntity(teacherService.addLanguage(teacherId, languageId));
    }

    @DeleteMapping("/{teacherId}/languages/{languageId}")
    public TeacherResponseDTO removeLanguage(@PathVariable Long teacherId, @PathVariable Long languageId) {
        return TeacherResponseDTO.fromEntity(teacherService.removeLanguage(teacherId, languageId));
    }

    @GetMapping ("/by-language/{languageId}")
    public List<TeacherResponseDTO> getByLanguage(@PathVariable Long languageId) {
        return teacherService.getByLanguage(languageId)
                .stream()
                .map(TeacherResponseDTO::fromEntity)
                .toList();
    }
}
