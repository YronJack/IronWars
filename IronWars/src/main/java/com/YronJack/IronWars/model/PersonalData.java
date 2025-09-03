package com.YronJack.IronWars.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonalData {

    @NotBlank(message = "The field name is necessary")
    @Size(max = 100, message = "Name hasn't contained more than 100 characters")
    private String name;

    @NotBlank(message = "The field Last Name is necessary")
    @Size(max = 100, message = "Last Name hasn't contained more than 100 characters")
    private String lastName;

    @NotBlank(message = "The field address is necessary")
    private String address;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    @Size(max = 150, message = "El email no puede tener más de 150 caracteres")
    @Column(nullable = true, length = 150, unique = true)
    private String email;

    private String phone;

}
