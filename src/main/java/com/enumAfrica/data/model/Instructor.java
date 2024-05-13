package com.enumAfrica.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User{
    private Long organizationId;
    @OneToMany
    private List<Course> courses;
    @Enumerated(EnumType.STRING)
    private Status instructorStatus;
    private LocalDate dateAdded;
    private LocalDate lastActivity;
}
