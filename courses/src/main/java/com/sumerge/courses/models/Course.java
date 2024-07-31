package com.sumerge.courses.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Course {
    String id;
    String name;
    String description;
    int credit;

}
