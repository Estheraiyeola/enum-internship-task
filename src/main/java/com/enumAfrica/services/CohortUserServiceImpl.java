package com.enumAfrica.services;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.User;
import com.enumAfrica.dto.request.RemoveInstructorRequest;
import com.enumAfrica.dto.response.RemovedInstructorResponse;
import com.enumAfrica.exception.CohortNotFoundException;
import com.enumAfrica.exception.UserWithThisCredentialsDoesNotExistException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CohortUserServiceImpl implements CohortUserService{
    private UserService userService;
    private CohortService cohortService;

    @Override
    @Transactional
    public RemovedInstructorResponse removeInstructorFromCohort(RemoveInstructorRequest removeInstructorRequest) throws CohortNotFoundException, UserWithThisCredentialsDoesNotExistException {
        User foundInstructor = userService.findById(removeInstructorRequest.getInstructorId());
        Cohort foundCohort = cohortService.findById(removeInstructorRequest.getCohortId());

        if (foundCohort != null){
            for (Cohort cohort:cohortService.findAll()) {
                if (cohort.getId().equals(foundCohort.getId())){
                    cohort.getUsers().remove(foundInstructor);
                    cohortService.save(cohort);
                }
            }
            RemovedInstructorResponse removedInstructorResponse = new RemovedInstructorResponse();
            removedInstructorResponse.setMessage("Instructor removed from the cohort");
            return removedInstructorResponse;
        }

        throw new UserWithThisCredentialsDoesNotExistException("User Doesn't Exist");
    }

    @Override
    public void deleteUser(User user) {
        cohortService.deleteUser(user);
    }
    @Override
    @Transactional
    public void deleteAll() {
        for (User user:userService.findAll()) {
            deleteUser(user);
            userService.delete(user);
        }
    }
}
