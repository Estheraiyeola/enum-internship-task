package com.enumAfrica.services;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.dto.request.CreateCohortRequest;
import com.enumAfrica.dto.response.CreatedCohortResponse;
import com.enumAfrica.exception.CohortAlreadyExistsException;
import com.enumAfrica.exception.ProgramTypeDoesNotExistException;

import java.io.IOException;
import java.util.List;

public interface CohortService {
    CreatedCohortResponse createCohort(CreateCohortRequest createCohortRequest) throws CohortAlreadyExistsException, IOException, ProgramTypeDoesNotExistException;

    Cohort getCohort(String aceClan);

    void deleteAll();

    List<Cohort> getAllCohorts();
}
