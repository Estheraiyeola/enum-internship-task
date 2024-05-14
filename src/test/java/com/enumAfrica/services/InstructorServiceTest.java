package com.enumAfrica.services;

import com.enumAfrica.data.model.Instructor;
import com.enumAfrica.data.model.Recipient;
import com.enumAfrica.data.model.Role;
import com.enumAfrica.dto.request.*;
import com.enumAfrica.dto.response.*;
import com.enumAfrica.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class InstructorServiceTest {
    @Autowired
    private InstructorService instructorService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CohortCourseService cohortCourseService;
    @Autowired
    private ProgramTypeService programTypeService;
    @Autowired
    private CohortService cohortService;
    @Autowired
    private CohortUserService cohortUserService;
    @Autowired
    private CourseService courseService;

    @BeforeEach
    public void setInstructorService(){
        cohortUserService.deleteAll();
        organizationService.deleteAll();
        courseService.deleteAll();
        programTypeService.deleteAll();
        cohortService.deleteAll();
    }

    @Test
    public void testThatInstructorCan_Be_Assigned_A_Course() throws UserAlreadyExistsException, OrganizationAlreadyExistsException, CourseAlreadyExistsException, ProgramAlreadyExistsException, CohortNotFoundException, CourseNotFoundException, CohortAlreadyExistsException, ProgramTypeDoesNotExistException, IOException {
        RegisterOrganizationRequest registerOrganizationRequest = new RegisterOrganizationRequest();
        registerOrganizationRequest.setName("Semicolon Africa");
        registerOrganizationRequest.setCac("1234Semicolon");
        registerOrganizationRequest.setEmail("semicolonafrica.com");
        registerOrganizationRequest.setPassword("password");

        RegisteredOrganizationResponse response = organizationService.registerOrganization(registerOrganizationRequest);
        assertThat(response.getMessage(), is("Organization Registered Successfully"));

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Tomide");
        createUserRequest.setLastName("Muliyu");
        createUserRequest.setEmail("tomide@gmail.com");
        createUserRequest.setPassword("password");
        createUserRequest.setRole(Role.INSTRUCTOR);
        createUserRequest.setOrganizationId(response.getOrganization().getId());

        CreatedUserResponse createdUserResponse = userService.createUser(createUserRequest);
        assertThat(createdUserResponse.getUser().getFirstName(), is("Tomide"));

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
        createCohortRequest.setOrganization(response.getOrganization());


        CreatedCohortResponse createdCohortResponse = cohortService.createCohort(createCohortRequest);
        System.out.println(createdCohortResponse.getCohort().getAvatar());
        assertThat(createdCohortResponse.getCohort().getName(), is("Ace clan"));

        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setName("Design Thinking");
        CreatedCourseResponse createdCourseResponse = cohortCourseService.createCourse(createCourseRequest);
        assertThat(createdCourseResponse.getCourse().getName(), is("Design Thinking"));


        AddCourseToCohortRequest addCourseToCohortRequest = new AddCourseToCohortRequest();
        addCourseToCohortRequest.setCourseId(createdCourseResponse.getCourse().getId());
        addCourseToCohortRequest.setCohortId(createdCohortResponse.getCohort().getId());

        AddedCourseToCohortResponse addedCourseToCohortResponse = cohortCourseService.addCourse(addCourseToCohortRequest);
        assertThat(addedCourseToCohortResponse.getMessage(), is("Course added successfully"));

        Recipient recepient = new Recipient();
        recepient.setEmail(createdUserResponse.getUser().getEmail());
        recepient.setName(createdUserResponse.getUser().getFirstName());


        InviteInstructorRequest inviteInstructorRequest = new InviteInstructorRequest();
        inviteInstructorRequest.setCohortId(createdCohortResponse.getCohort().getId());
        inviteInstructorRequest.setInstructorEmail(List.of(recepient));

        InvitedInstructorResponse invitedInstructorResponse = cohortService.inviteInstructorToCohort(inviteInstructorRequest);
        assertThat(invitedInstructorResponse.getMessage(), is("Invite successfully sent"));

        AcceptInviteRequest acceptInviteRequest = new AcceptInviteRequest();
        acceptInviteRequest.setInstructor((Instructor)createdUserResponse.getUser());
        acceptInviteRequest.setCohortId(createdCohortResponse.getCohort().getId());


        AcceptedInstructorInviteResponse acceptedInstructorInviteResponse = cohortService.acceptInstructorInvite(acceptInviteRequest);
        assertThat(acceptedInstructorInviteResponse.getMessage(), is("Congrats!!!, you're now an instructor"));
        assertThat(acceptedInstructorInviteResponse.getInstructor().getEmail(), is("tomide@gmail.com"));

        AssignCourseToInstructorRequest assignCourseToInstructorRequest = new AssignCourseToInstructorRequest();
        assignCourseToInstructorRequest.setCourseId(createdCourseResponse.getCourse().getId());
        assignCourseToInstructorRequest.setInstructorId(createdUserResponse.getUser().getId());

        AssignedCourseToInstructorResponse assignedCourseToInstructorResponse = instructorService.assignCourseToCohort(assignCourseToInstructorRequest);
        assertThat(assignedCourseToInstructorResponse.getInstructor().getCourses().get(0).getName(), is("Design Thinking"));

    }
}
