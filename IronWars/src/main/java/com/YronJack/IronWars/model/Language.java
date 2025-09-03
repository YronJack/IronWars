package com.YronJack.IronWars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    @Column(nullable = false, unique =true, length = 100)
    private String  name;
    private String  domain; //area de dominio: HTML,CSS

    //Profesores expertos en el lenguaje:
    @ManyToMany
    @JoinTable(
            name = "teacher_language",
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    @JsonIgnore
    private List<Teacher> teachers;
}
