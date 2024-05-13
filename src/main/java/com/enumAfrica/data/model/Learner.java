package com.enumAfrica.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Learner extends User{
    private int noOfPrograms;
    @Enumerated(EnumType.STRING)
    private Status learnerStatus;
    private Long organizationId;
    private LocalDate dateAdded;
    private LocalDate lastActivity;
}
