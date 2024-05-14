package com.enumAfrica.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long cohortId;
}
