package com.YronJack.IronWars.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer exam_id;

    adsfa

    @ManyToMany(mappedBy = "exams")
    private List<Student> students;
}
