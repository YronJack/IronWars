package com.YronJack.IronWars.dto.language;

import com.YronJack.IronWars.model.Language;
import lombok.Data;

@Data
public class LanguageResponseDTO {
    private Long id;
    private String name;
    private String domain;
}
