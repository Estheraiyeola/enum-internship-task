package com.enumAfrica.services;

import com.enumAfrica.dto.request.CreateCohortRequest;
import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.request.CreateProgramRequest;
import com.enumAfrica.dto.request.RegisterOrganizationRequest;
import com.enumAfrica.dto.response.CreatedCohortResponse;
import com.enumAfrica.dto.response.CreatedCourseResponse;
import com.enumAfrica.dto.response.CreatedProgramResponse;
import com.enumAfrica.dto.response.RegisteredOrganizationResponse;
import com.enumAfrica.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private ProgramTypeService programTypeService;
    @Autowired
    private CohortService cohortService;
    @Autowired
    private CohortCourseService cohortCourseService;
    @BeforeEach
    public void setCourseService(){
        courseService.deleteAll();
        programTypeService.deleteAll();
        cohortService.deleteAll();
        organizationService.deleteAll();
    }
    @Test
    public void testThatCourseCanBeCreated() throws CourseAlreadyExistsException, OrganizationAlreadyExistsException, ProgramAlreadyExistsException, CohortAlreadyExistsException, ProgramTypeDoesNotExistException, IOException, CohortNotFoundException {
        RegisterOrganizationRequest registerOrganizationRequest = new RegisterOrganizationRequest();
        registerOrganizationRequest.setName("Semicolon Africa");
        registerOrganizationRequest.setEmail("hdbsjksknjshfj.com");
        registerOrganizationRequest.setCac("667889jhyu");
        registerOrganizationRequest.setPassword("password");

        RegisteredOrganizationResponse registeredOrganizationResponse = organizationService.registerOrganization(registerOrganizationRequest);
        assertThat(registeredOrganizationResponse.getOrganization().getName(), is("Semicolon Africa"));

        CreateProgramRequest createProgramRequest = new CreateProgramRequest();
        createProgramRequest.setName("Java");

        CreatedProgramResponse createdProgramResponse = programTypeService.createProgram(createProgramRequest);

        assertThat(createdProgramResponse.getProgramType().getName(), is("Java"));

        CreateCohortRequest createCohortRequest = new CreateCohortRequest();
        createCohortRequest.setName("Ace clan");
        createCohortRequest.setDescription("Cohort 17");
        createCohortRequest.setAvatar("yikkhgfddd");
        createCohortRequest.setProgramType("Java");
        createCohortRequest.setStartDate("11-04-2023");
        createCohortRequest.setEndDate("11-04-2024");
        createCohortRequest.setOrganization(registeredOrganizationResponse.getOrganization());


        CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);
        System.out.println(response.getCohort().getAvatar());
        assertThat(response.getCohort().getName(), is("Ace clan"));

        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setName("Java");
        createCourseRequest.setCohortId(response.getCohort().getId());

        CreatedCourseResponse createdCourseResponse = cohortCourseService.createCourse(createCourseRequest);
        assertThat(createdCourseResponse.getCourse().getName(), is("Java"));

    }
    @Test
    public void testThatUniqueCoursesCanBeCreated() throws CourseAlreadyExistsException, CohortAlreadyExistsException, ProgramTypeDoesNotExistException, IOException, OrganizationAlreadyExistsException, ProgramAlreadyExistsException, CohortNotFoundException {
        RegisterOrganizationRequest registerOrganizationRequest = new RegisterOrganizationRequest();
        registerOrganizationRequest.setName("Semicolon Africa");
        registerOrganizationRequest.setEmail("hdbsjksknjshfj.com");
        registerOrganizationRequest.setCac("667889jhyu");
        registerOrganizationRequest.setPassword("password");

        RegisteredOrganizationResponse registeredOrganizationResponse = organizationService.registerOrganization(registerOrganizationRequest);
        assertThat(registeredOrganizationResponse.getOrganization().getName(), is("Semicolon Africa"));

        CreateProgramRequest createProgramRequest = new CreateProgramRequest();
        createProgramRequest.setName("Java");

        CreatedProgramResponse createdProgramResponse = programTypeService.createProgram(createProgramRequest);

        assertThat(createdProgramResponse.getProgramType().getName(), is("Java"));

        CreateCohortRequest createCohortRequest = new CreateCohortRequest();
        createCohortRequest.setName("Ace clan");
        createCohortRequest.setDescription("Cohort 17");
        createCohortRequest.setAvatar("yikkhgfddd");
        createCohortRequest.setProgramType("Java");
        createCohortRequest.setStartDate("11-04-2023");
        createCohortRequest.setEndDate("11-04-2024");
        createCohortRequest.setOrganization(registeredOrganizationResponse.getOrganization());


        CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);
        System.out.println(response.getCohort().getAvatar());
        assertThat(response.getCohort().getName(), is("Ace clan"));

        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setName("Java");
        createCourseRequest.setCohortId(response.getCohort().getId());

        CreatedCourseResponse createdCourseResponse = cohortCourseService.createCourse(createCourseRequest);
        assertThat(createdCourseResponse.getCourse().getName(), is("Java"));

        assertThrows(CourseAlreadyExistsException.class, ()->{cohortCourseService.createCourse(createCourseRequest);});

    }
}