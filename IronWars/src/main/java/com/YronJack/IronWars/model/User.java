package com.YronJack.IronWars.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class  User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The field nickName is necessary")
    @Size(max = 100, message = "Nickname hasn't contained more than 100 characters")
    private String nickName;


    @Size(min = 8, max = 100, message = "The password hasn't contained more than 100 characters")
    @Column(nullable = false, length = 100)
    private String password;

    @Embedded
    private PersonalData personalData;

}
