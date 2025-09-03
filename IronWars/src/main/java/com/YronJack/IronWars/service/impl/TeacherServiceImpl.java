package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.model.Language;
import com.YronJack.IronWars.model.Teacher;
import com.YronJack.IronWars.repository.LanguageRepository;
import com.YronJack.IronWars.repository.TeacherRepository;
import com.YronJack.IronWars.service.interfaces.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final LanguageRepository languageRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              LanguageRepository languageRepository) {
        this.teacherRepository = teacherRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher getById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher create(Teacher teacher) {
        if (teacher.getPersonalData() != null
                && teacher.getPersonalData().getName() != null
                && teacherRepository.existsByPersonalData_Name(teacher.getPersonalData().getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already in use");
        }
        if (teacher.getPersonalData() != null
                && teacher.getPersonalData().getEmail() != null
                && teacherRepository.existsByPersonalData_Email(teacher.getPersonalData().getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Long id, Teacher update) {
        Teacher existing = getById(id);
        existing.setNickName(update.getNickName());
        existing.setPassword(update.getPassword());
        existing.setPersonalData(update.getPersonalData());
        existing.setYearsExperience(update.getYearsExperience());
        existing.setRating(update.getRating());
        return teacherRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.delete(getById(id));
    }

    @Override
    public Teacher addLanguage(Long teacherId, Long languageId) {
        Teacher teacher = getById(teacherId);
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));

        List<Language> langs = teacher.getLanguages();
        if (langs == null) {
            langs = new ArrayList<>();
            teacher.setLanguages(langs);
        }
        boolean already = langs.stream().anyMatch(l -> l.getId().equals(languageId));
        if (!already) {
            langs.add(language);
        }
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher removeLanguage(Long teacherId, Long languageId) {
        Teacher teacher = getById(teacherId);
        List<Language> langs = teacher.getLanguages();
        if (langs != null) {
            langs.removeIf(l -> l.getId().equals(languageId));
        }
        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> getByLanguage(Long languageId) {
        return teacherRepository.findByLanguages_Id(languageId);
    }
}
