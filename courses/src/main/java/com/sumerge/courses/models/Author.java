package com.sumerge.courses.models;

import java.util.Set;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Setter
@Getter
public class Author {

    @Id
    @GeneratedValue
    Integer id;
    
    String name;
    
    @Column(
        nullable = false,
        unique = true
    )
    String email;

    LocalDate birthDate;

    @ManyToMany(mappedBy = "authors")
    Set<Course> courses;

    
}
