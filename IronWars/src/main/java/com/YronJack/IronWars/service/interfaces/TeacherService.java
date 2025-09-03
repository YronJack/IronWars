package com.YronJack.IronWars.service.interfaces;

import com.YronJack.IronWars.model.PersonalData;
import com.YronJack.IronWars.model.Teacher;

import java.util.List;

public interface TeacherService {
    //CRUD
    Teacher getById(Long id);
    List<Teacher> getAll();
    Teacher create(Teacher teacher);
    Teacher update(Long id, Teacher update);
    void delete(Long id);

    //Otros
    Teacher addLanguage (Long teacherId, Long languageId);
    Teacher removeLanguage(Long teacherId, Long languageId);
    List<Teacher> getByLanguage(Long languageId);

}
