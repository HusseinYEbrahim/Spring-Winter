package com.sumerge.courses.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
// Shouldn't be sent to other modules 
public class Course {
    
    @Id
    @GeneratedValue
    Integer id;

    @Column(
        nullable = false
    )
    String name;

    String description;
    
    @Column(
        nullable = false
    )
    int credit;

    @OneToMany(mappedBy = "course")
    Set<Rating> ratings;

    @OneToOne(cascade = CascadeType.ALL)
    Assessment assessment;

    @ManyToMany
    Set<Author> authors;
}
