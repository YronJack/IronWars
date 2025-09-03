package com.YronJack.IronWars.repository;

import com.YronJack.IronWars.enums.ExperienceLevel;
import com.YronJack.IronWars.model.Exam;
import com.YronJack.IronWars.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByExperienceLevel(ExperienceLevel experienceLevel);
}
