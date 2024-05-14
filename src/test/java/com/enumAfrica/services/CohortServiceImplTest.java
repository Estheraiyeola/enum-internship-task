package com.enumAfrica.services;

import com.enumAfrica.data.model.*;
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

public class CohortServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ProgramTypeService programTypeService;
    @Autowired
    private CohortService cohortService;
    @Autowired
    private CohortUserService cohortUserService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CohortCourseService cohortCourseService;

    @BeforeEach
    public void setCohortService(){
        cohortUserService.deleteAll();
        programTypeService.deleteAll();
        cohortService.deleteAll();
        organizationService.deleteAll();
        courseService.deleteAll();
    }
    @Test
    public void testThatCohortCanBeCreated() throws CohortAlreadyExistsException, IOException, ProgramTypeDoesNotExistException, OrganizationAlreadyExistsException, ProgramAlreadyExistsException {
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
    }
    @Test
    public void testThat_A_CohortCanBeGotten() throws CohortAlreadyExistsException, IOException, ProgramTypeDoesNotExistException, ProgramAlreadyExistsException, OrganizationAlreadyExistsException {
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
        createCohortRequest.setAvatar("photo");
        createCohortRequest.setProgramType("Java");
        createCohortRequest.setStartDate("11-04-2023");
        createCohortRequest.setEndDate("11-04-2024");
        createCohortRequest.setOrganization(registeredOrganizationResponse.getOrganization());


        CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);
        System.out.println(response.getCohort().getAvatar());
        assertThat(response.getCohort().getName(), is("Ace clan"));

        Cohort foundCohort = cohortService.getCohort("Ace clan");
        assertThat(foundCohort.getDescription(), is("Cohort 17"));
    }
    @Test
    public void testThat_All_CohortsCanBeGotten() throws CohortAlreadyExistsException, IOException, ProgramTypeDoesNotExistException, ProgramAlreadyExistsException, OrganizationAlreadyExistsException {
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
        createCohortRequest.setAvatar("photo");
        createCohortRequest.setProgramType("Java");
        createCohortRequest.setStartDate("11-04-2023");
        createCohortRequest.setEndDate("11-04-2024");
        createCohortRequest.setOrganization(registeredOrganizationResponse.getOrganization());

        CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);
        System.out.println(response.getCohort().getAvatar());
        assertThat(response.getCohort().getName(), is("Ace clan"));

        CreateCohortRequest createCohortRequest2 = new CreateCohortRequest();
        createCohortRequest2.setName("Elite");
        createCohortRequest2.setDescription("Cohort 15");
        createCohortRequest2.setAvatar("photo");
        createCohortRequest2.setProgramType("Java");
        createCohortRequest2.setStartDate("11-04-2023");
        createCohortRequest2.setEndDate("11-04-2024");
        createCohortRequest2.setOrganization(registeredOrganizationResponse.getOrganization());


        CreatedCohortResponse response2 = cohortService.createCohort(createCohortRequest2);
        System.out.println(response2.getCohort().getAvatar());
        assertThat(response2.getCohort().getName(), is("Elite"));

        List<Cohort> foundCohorts = cohortService.getAllCohorts();
        assertThat(foundCohorts.size(), is(2));
    }
    @Test
    public void testInstructorCanBeInvitedToCohort() throws CohortAlreadyExistsException, ProgramTypeDoesNotExistException, IOException, UserAlreadyExistsException, ProgramAlreadyExistsException, OrganizationAlreadyExistsException {
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

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Tomide");
        createUserRequest.setLastName("Muliyu");
        createUserRequest.setEmail("tomide@gmail.com");
        createUserRequest.setPassword("password");
        createUserRequest.setRole(Role.INSTRUCTOR);

        CreatedUserResponse createdUserResponse = userService.createUser(createUserRequest);
        assertThat(createdUserResponse.getUser().getFirstName(), is("Tomide"));

        Long cohortId = response.getCohort().getId();
        Recipient recepient = new Recipient();
        recepient.setEmail(createdUserResponse.getUser().getEmail());
        recepient.setName(createdUserResponse.getUser().getFirstName());

        InviteInstructorRequest inviteInstructorRequest = new InviteInstructorRequest();
        inviteInstructorRequest.setCohortId(cohortId);
        inviteInstructorRequest.setInstructorEmail(List.of(recepient));

        InvitedInstructorResponse invitedInstructorResponse = cohortService.inviteInstructorToCohort(inviteInstructorRequest);
        assertThat(invitedInstructorResponse.getMessage(), is("Invite successfully sent"));

    }
    @Test
    public void testThatInstructorCanAcceptInviteAndBeAddedToTheCohortAs_An_Instructor() throws CohortAlreadyExistsException, ProgramTypeDoesNotExistException, IOException, UserAlreadyExistsException, ProgramAlreadyExistsException, CohortNotFoundException, OrganizationAlreadyExistsException {
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

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Tomide");
        createUserRequest.setLastName("Muliyu");
        createUserRequest.setEmail("tomide@gmail.com");
        createUserRequest.setPassword("password");
        createUserRequest.setRole(Role.INSTRUCTOR);

        CreatedUserResponse createdUserResponse = userService.createUser(createUserRequest);
        assertThat(createdUserResponse.getUser().getFirstName(), is("Tomide"));

        Long cohortId = response.getCohort().getId();
        Recipient recepient = new Recipient();
        recepient.setEmail(createdUserResponse.getUser().getEmail());
        recepient.setName(createdUserResponse.getUser().getFirstName());

        InviteInstructorRequest inviteInstructorRequest = new InviteInstructorRequest();
        inviteInstructorRequest.setCohortId(cohortId);
        inviteInstructorRequest.setInstructorEmail(List.of(recepient));

        InvitedInstructorResponse invitedInstructorResponse = cohortService.inviteInstructorToCohort(inviteInstructorRequest);
        assertThat(invitedInstructorResponse.getMessage(), is("Invite successfully sent"));

        AcceptInviteRequest acceptInviteRequest = new AcceptInviteRequest();
        acceptInviteRequest.setInstructor((Instructor)createdUserResponse.getUser());
        acceptInviteRequest.setCohortId(cohortId);


        AcceptedInstructorInviteResponse acceptedInstructorInviteResponse = cohortService.acceptInstructorInvite(acceptInviteRequest);
        assertThat(acceptedInstructorInviteResponse.getMessage(), is("Congrats!!!, you're now an instructor"));
        assertThat(acceptedInstructorInviteResponse.getInstructor().getEmail(), is("tomide@gmail.com"));

    }

    @Test
    public void testThatAn_InstructorCanBe_RemovedFromCohort() throws CohortAlreadyExistsException, ProgramTypeDoesNotExistException, IOException, UserAlreadyExistsException, ProgramAlreadyExistsException, CohortNotFoundException, UserWithThisCredentialsDoesNotExistException, OrganizationAlreadyExistsException {
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

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Tomide");
        createUserRequest.setLastName("Muliyu");
        createUserRequest.setEmail("tomide@gmail.com");
        createUserRequest.setPassword("password");
        createUserRequest.setRole(Role.INSTRUCTOR);

        CreatedUserResponse createdUserResponse = userService.createUser(createUserRequest);
        assertThat(createdUserResponse.getUser().getFirstName(), is("Tomide"));

        Long cohortId = response.getCohort().getId();
        Recipient recepient = new Recipient();
        recepient.setEmail(createdUserResponse.getUser().getEmail());
        recepient.setName(createdUserResponse.getUser().getFirstName());

        InviteInstructorRequest inviteInstructorRequest = new InviteInstructorRequest();
        inviteInstructorRequest.setCohortId(cohortId);
        inviteInstructorRequest.setInstructorEmail(List.of(recepient));

        InvitedInstructorResponse invitedInstructorResponse = cohortService.inviteInstructorToCohort(inviteInstructorRequest);
        assertThat(invitedInstructorResponse.getMessage(), is("Invite successfully sent"));

        AcceptInviteRequest acceptInviteRequest = new AcceptInviteRequest();
        acceptInviteRequest.setInstructor((Instructor)createdUserResponse.getUser());
        acceptInviteRequest.setCohortId(cohortId);


        AcceptedInstructorInviteResponse acceptedInstructorInviteResponse = cohortService.acceptInstructorInvite(acceptInviteRequest);
        assertThat(acceptedInstructorInviteResponse.getMessage(), is("Congrats!!!, you're now an instructor"));
        assertThat(acceptedInstructorInviteResponse.getInstructor().getEmail(), is("tomide@gmail.com"));

        RemoveInstructorRequest removeInstructorRequest = new RemoveInstructorRequest();
        removeInstructorRequest.setInstructorId(createdUserResponse.getUser().getId());
        removeInstructorRequest.setCohortId(cohortId);

        RemovedInstructorResponse removedInstructorResponse = cohortUserService.removeInstructorFromCohort(removeInstructorRequest);
        assertThat(removedInstructorResponse.getMessage(), is("Instructor removed from the cohort"));
    }

    @Test
    public void testA_Course_Can_Be_Added_To_A_Cohort() throws CohortAlreadyExistsException, ProgramTypeDoesNotExistException, IOException, ProgramAlreadyExistsException, OrganizationAlreadyExistsException, CohortNotFoundException, CourseAlreadyExistsException, CourseNotFoundException {
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
        createCourseRequest.setName("Design Thinking");
        CreatedCourseResponse createdCourseResponse = cohortCourseService.createCourse(createCourseRequest);
        assertThat(createdCourseResponse.getCourse().getName(), is("Design Thinking"));


        AddCourseToCohortRequest addCourseToCohortRequest = new AddCourseToCohortRequest();
        addCourseToCohortRequest.setCourseId(createdCourseResponse.getCourse().getId());
        addCourseToCohortRequest.setCohortId(response.getCohort().getId());

        AddedCourseToCohortResponse addedCourseToCohortResponse = cohortCourseService.addCourse(addCourseToCohortRequest);
        assertThat(addedCourseToCohortResponse.getMessage(), is("Course added successfully"));


    }

    @Test
    public void testThatLearnerCanBeInvitedTo_Cohort() throws CohortAlreadyExistsException, ProgramTypeDoesNotExistException, IOException, ProgramAlreadyExistsException, OrganizationAlreadyExistsException, UserAlreadyExistsException {
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

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Ashley");
        createUserRequest.setLastName("Ndabai");
        createUserRequest.setEmail("ashley@gmail.com");
        createUserRequest.setPassword("password");
        createUserRequest.setRole(Role.LEARNER);
        createUserRequest.setOrganizationId(registeredOrganizationResponse.getOrganization().getId());

        CreatedUserResponse createdUserResponse = userService.createUser(createUserRequest);
        assertThat(createdUserResponse.getUser().getFirstName(), is("Ashley"));

        Recipient recipient = new Recipient();
        recipient.setName(createdUserResponse.getUser().getFirstName());
        recipient.setEmail(createdUserResponse.getUser().getEmail());

        InviteLearnerRequest inviteLearnerRequest = new InviteLearnerRequest();
        inviteLearnerRequest.setLearnerEmail(List.of(recipient));
        inviteLearnerRequest.setLink("ooiuwiduyh");
        inviteLearnerRequest.setCohortId(response.getCohort().getId());

        InvitedLearnerResponse invitedLearnerResponse = cohortService.inviteLearnerToCohort(inviteLearnerRequest);
        assertThat(invitedLearnerResponse.getMessage(), is("Invite successfully sent"));


    }

    @Test
    public void testThatLearnerCan_AcceptInvite() throws CohortAlreadyExistsException, ProgramTypeDoesNotExistException, IOException, ProgramAlreadyExistsException, OrganizationAlreadyExistsException, UserAlreadyExistsException, CohortNotFoundException {
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

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Ashley");
        createUserRequest.setLastName("Ndabai");
        createUserRequest.setEmail("ashley@gmail.com");
        createUserRequest.setPassword("password");
        createUserRequest.setRole(Role.LEARNER);
        createUserRequest.setOrganizationId(registeredOrganizationResponse.getOrganization().getId());

        CreatedUserResponse createdUserResponse = userService.createUser(createUserRequest);
        assertThat(createdUserResponse.getUser().getFirstName(), is("Ashley"));

        Recipient recipient = new Recipient();
        recipient.setName(createdUserResponse.getUser().getFirstName());
        recipient.setEmail(createdUserResponse.getUser().getEmail());

        InviteLearnerRequest inviteLearnerRequest = new InviteLearnerRequest();
        inviteLearnerRequest.setLearnerEmail(List.of(recipient));
        inviteLearnerRequest.setLink("ooiuwiduyh");
        inviteLearnerRequest.setCohortId(response.getCohort().getId());

        InvitedLearnerResponse invitedLearnerResponse = cohortService.inviteLearnerToCohort(inviteLearnerRequest);
        assertThat(invitedLearnerResponse.getMessage(), is("Invite successfully sent"));

        AcceptLearnerInviteRequest acceptInviteRequest = new AcceptLearnerInviteRequest();
        acceptInviteRequest.setLearner((Learner) createdUserResponse.getUser());
        acceptInviteRequest.setCohortId(response.getCohort().getId());


        AcceptedLearnerInviteResponse acceptedLearnerInviteResponse = cohortService.acceptLearnerInvite(acceptInviteRequest);
        assertThat(acceptedLearnerInviteResponse.getMessage(), is("Learner added"));
        assertThat(acceptedLearnerInviteResponse.getLearners().getEmail(), is("ashley@gmail.com"));


    }
}