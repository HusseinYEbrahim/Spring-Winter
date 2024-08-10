package com.sumerge.courses.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Assessment {

    @Id
    @GeneratedValue
    Integer id;

    String content;
    
    @OneToOne(mappedBy = "assessment")
    Course course;
}
