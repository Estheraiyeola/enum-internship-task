package com.enumAfrica.services;

import com.enumAfrica.data.model.User;
import com.enumAfrica.dto.request.RemoveInstructorRequest;
import com.enumAfrica.dto.response.RemovedInstructorResponse;
import com.enumAfrica.exception.CohortNotFoundException;
import com.enumAfrica.exception.UserWithThisCredentialsDoesNotExistException;
import jakarta.transaction.Transactional;

public interface CohortUserService {
    RemovedInstructorResponse removeInstructorFromCohort(RemoveInstructorRequest removeInstructorRequest) throws CohortNotFoundException, UserWithThisCredentialsDoesNotExistException;

    void deleteUser(User user);

    @Transactional
    void deleteAll();
}
