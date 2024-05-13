package com.enumAfrica.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class Cohort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String programType;
    private String startDate;
    private String endDate;
    private String avatar;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "cohort_users",
            joinColumns = @JoinColumn(name = "cohort_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private List<User> users;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "organization_id")
    private Organization organization;
    @OneToMany
    private List<Course> courses;
}
