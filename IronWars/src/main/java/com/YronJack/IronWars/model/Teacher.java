package com.YronJack.IronWars.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends User {
    private Integer yearsExperience;
    private Double rating;

    @ManyToMany(mappedBy = "teachers")
    private List<Student> students;

}
