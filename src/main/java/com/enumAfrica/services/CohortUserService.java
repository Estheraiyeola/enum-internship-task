package com.enumAfrica.services;

import com.enumAfrica.dto.request.RemoveInstructorRequest;
import com.enumAfrica.dto.response.RemovedInstructorResponse;
import com.enumAfrica.exception.CohortNotFoundException;
import com.enumAfrica.exception.UserWithThisCredentialsDoesNotExistException;

public interface CohortUserService {
    RemovedInstructorResponse removeInstructorFromCohort(RemoveInstructorRequest removeInstructorRequest) throws CohortNotFoundException, UserWithThisCredentialsDoesNotExistException;
}
