package com.enumAfrica.utils;

import com.enumAfrica.data.model.*;
import com.enumAfrica.dto.request.CreateCohortRequest;
import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.dto.request.RegisterOrganizationRequest;
import com.enumAfrica.dto.response.CreatedCohortResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class Mapper {
    public Cohort map(CreateCohortRequest createCohortRequest) throws IOException {
        Cohort newCohort = new Cohort();
        newCohort.setAvatar(createCohortRequest.getAvatar());
        newCohort.setDescription(createCohortRequest.getDescription());
        newCohort.setName(createCohortRequest.getName());
        newCohort.setStartDate(createCohortRequest.getStartDate());
        newCohort.setEndDate(createCohortRequest.getEndDate());
        newCohort.setProgramType(createCohortRequest.getProgramType());
        newCohort.setOrganization(createCohortRequest.getOrganization());
        return newCohort;
    }


    public CreatedCohortResponse map(Cohort savedCohort) {
        CreatedCohortResponse createdCohortResponse = new CreatedCohortResponse();
        createdCohortResponse.setCohort(savedCohort);
        createdCohortResponse.setMessage("Cohort Created Successfully");
        return createdCohortResponse;
    }
    public User map(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setEmail(createUserRequest.getEmail());
        String encodedPassword = getEncryptedPassword(createUserRequest);
        user.setPassword(encodedPassword);
        user.setRole(createUserRequest.getRole());
        return user;
    }
    private static String getEncryptedPassword(CreateUserRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(request.getPassword());
    }
    public Course map(CreateCourseRequest createCourseRequest) {
        Course course = new Course();
        course.setName(createCourseRequest.getName());
        return course;
    }

    public Organization map(RegisterOrganizationRequest registerOrganizationRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registerOrganizationRequest.getPassword());

        Organization organization = new Organization();
        organization.setName(registerOrganizationRequest.getName());
        organization.setCac(registerOrganizationRequest.getCac());
        organization.setEmail(registerOrganizationRequest.getEmail());
        organization.setPassword(encodedPassword);
        organization.setRole(Role.ORGANIZATION);

        return organization;
    }

    public Instructor mapInstructor(CreateUserRequest createUserRequest) {
        Instructor user = new Instructor();
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setEmail(createUserRequest.getEmail());
        String encodedPassword = getEncryptedPassword(createUserRequest);
        user.setPassword(encodedPassword);
        user.setRole(createUserRequest.getRole());
        user.setOrganizationId(createUserRequest.getOrganizationId());
        user.setDateAdded(LocalDate.now());
        user.setCourses(createUserRequest.getCourses());
        user.setInstructorStatus(Status.ACTIVE);
        return user;
    }

    public User mapLearner(CreateUserRequest createUserRequest) {
        Learner user = new Learner();
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setEmail(createUserRequest.getEmail());
        String encodedPassword = getEncryptedPassword(createUserRequest);
        user.setPassword(encodedPassword);
        user.setRole(createUserRequest.getRole());
        user.setNoOfPrograms(0);
        user.setOrganizationId(createUserRequest.getOrganizationId());
        user.setDateAdded(LocalDate.now());
        user.setLearnerStatus(Status.ACTIVE);
        return user;
    }

    public User mapAdmin(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setEmail(createUserRequest.getEmail());
        String encodedPassword = getEncryptedPassword(createUserRequest);
        user.setPassword(encodedPassword);
        user.setRole(createUserRequest.getRole());
        return user;
    }
}
