package com.enumAfrica.services;

import com.enumAfrica.data.model.*;
import com.enumAfrica.dto.request.*;
import com.enumAfrica.dto.response.*;
import com.enumAfrica.exception.*;

import java.io.IOException;
import java.util.List;

public interface CohortService {
    CreatedCohortResponse createCohort(CreateCohortRequest createCohortRequest) throws CohortAlreadyExistsException, IOException, ProgramTypeDoesNotExistException;

    Cohort getCohort(String aceClan);

    void deleteAll();

    List<Cohort> getAllCohorts();

    InvitedInstructorResponse inviteInstructorToCohort(InviteInstructorRequest inviteInstructorRequest);

    AcceptedInstructorInviteResponse acceptInstructorInvite(AcceptInviteRequest cohortId) throws CohortNotFoundException;
    
    void deleteUser(User user);

    Cohort findById(Long cohortId) throws CohortNotFoundException;

    List<Cohort> findAll();

    void save(Cohort cohort);

    AddedCourseToCohortResponse addCourse(AddCourseToCohortRequest addCourseToCohortRequest) throws CohortNotFoundException, CourseNotFoundException;

    List<Cohort> findCohortByOrganization(Organization organization);

    void deleteCohorts(List<Cohort> cohortList);

    InvitedLearnerResponse inviteLearnerToCohort(InviteLearnerRequest inviteLearnerRequest);

    AcceptedLearnerInviteResponse acceptLearnerInvite(AcceptLearnerInviteRequest acceptInviteRequest) throws CohortNotFoundException;

    List<Course> getAllCoursesInA_Cohort(Long cohortId) throws CohortNotFoundException;

    List<Learner> getAllLearnersIn_A_Cohort(Long cohortId) throws CohortNotFoundException;

    List<Instructor> getAllInstructorsIn_A_Cohort(Long cohortId) throws CohortNotFoundException;

    RemovedInstructorResponse removeInstructor(RemoveInstructorRequest request) throws CohortNotFoundException, UserWithThisCredentialsDoesNotExistException;
}
