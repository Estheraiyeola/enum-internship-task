package com.enumAfrica.dto.request;

import com.enumAfrica.data.model.Course;
import com.enumAfrica.data.model.Role;
import com.enumAfrica.data.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Long organizationId;
    private int noOfPrograms;
    private List<Course> courses;
    private Status userStatus;
    private LocalDate dateAdded;
    private LocalDate lastActivity;
}
