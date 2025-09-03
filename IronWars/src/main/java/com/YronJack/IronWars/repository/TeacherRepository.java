package com.YronJack.IronWars.repository;

import com.YronJack.IronWars.model.PersonalData;
import com.YronJack.IronWars.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findAllByPersonalData_Name(String name);
    boolean existsByPersonalData_NameAndPersonalData_LastName(String name, String lastName);

    //listar profesores expertos en un lenguaje
    List<Teacher> findByLanguages_Id(Long languageId);
}
