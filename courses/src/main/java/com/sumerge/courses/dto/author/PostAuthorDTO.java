package com.sumerge.courses.dto.author;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PostAuthorDTO {
    
    String name;

    LocalDate birthDate;

    String email;
}
