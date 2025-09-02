package com.YronJack.IronWars.dto.language;

import com.YronJack.IronWars.model.Language;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.aspectj.bridge.IMessage;

@Data
public class LanguageRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;
    private String domain;

}
