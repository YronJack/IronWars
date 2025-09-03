package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.teacher.TeacherRequestDTO;
import com.YronJack.IronWars.dto.teacher.TeacherResponseDTO;
import com.YronJack.IronWars.model.Teacher;
import com.YronJack.IronWars.service.interfaces.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@Tag(name = "Teachers", description = "Operations related to teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // CRUD

    @Operation(summary = "Get all teachers")
    @ApiResponse(responseCode = "200", description = "List of all teachers")
    @GetMapping
    public List<TeacherResponseDTO> getAll() {
        return teacherService.getAll()
                .stream()
                .map(TeacherResponseDTO::fromEntity)
                .toList();
    }

    @Operation(summary = "Get teacher by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Teacher found"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @GetMapping("/{id}")
    public TeacherResponseDTO getById(
            @Parameter(description = "ID of the teacher") @PathVariable Long id) {
        return TeacherResponseDTO.fromEntity(teacherService.getById(id));
    }

    @Operation(summary = "Create a new teacher")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Teacher created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponseDTO create(
            @Valid @RequestBody TeacherRequestDTO request) {
        Teacher saved = teacherService.create(request.toEntity());
        return TeacherResponseDTO.fromEntity(saved);
    }

    @Operation(summary = "Update a teacher by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Teacher updated"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @PutMapping("/{id}")
    public TeacherResponseDTO update(
            @Parameter(description = "ID of the teacher to update") @PathVariable Long id,
            @Valid @RequestBody TeacherRequestDTO request) {
        Teacher updated = teacherService.update(id, request.toEntity());
        return TeacherResponseDTO.fromEntity(updated);
    }

    @Operation(summary = "Delete a teacher by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Teacher deleted"),
            @ApiResponse(responseCode = "404", description = "Teacher not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "ID of the teacher to delete") @PathVariable Long id) {
        teacherService.delete(id);
    }

    // ---

    @Operation(summary = "Add a language to a teacher")
    @ApiResponse(responseCode = "200", description = "Language added to teacher")
    @PostMapping("/{teacherId}/languages/{languageId}")
    public TeacherResponseDTO addLanguage(
            @Parameter(description = "ID of the teacher") @PathVariable Long teacherId,
            @Parameter(description = "ID of the language") @PathVariable Long languageId) {
        return TeacherResponseDTO.fromEntity(teacherService.addLanguage(teacherId, languageId));
    }

    @Operation(summary = "Remove a language from a teacher")
    @ApiResponse(responseCode = "200", description = "Language removed from teacher")
    @DeleteMapping("/{teacherId}/languages/{languageId}")
    public TeacherResponseDTO removeLanguage(
            @Parameter(description = "ID of the teacher") @PathVariable Long teacherId,
            @Parameter(description = "ID of the language") @PathVariable Long languageId) {
        return TeacherResponseDTO.fromEntity(teacherService.removeLanguage(teacherId, languageId));
    }

    @Operation(summary = "Get teachers by language")
    @ApiResponse(responseCode = "200", description = "List of teachers by language")
    @GetMapping("/by-language/{languageId}")
    public List<TeacherResponseDTO> getByLanguage(
            @Parameter(description = "ID of the language") @PathVariable Long languageId) {
        return teacherService.getByLanguage(languageId)
                .stream()
                .map(TeacherResponseDTO::fromEntity)
                .toList();
    }
}