package com.YronJack.IronWars.dto.student;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentRequestDTO {
    private Long studentId;
    private String nickName;
}
