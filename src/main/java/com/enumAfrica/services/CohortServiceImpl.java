package com.enumAfrica.services;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.ProgramType;
import com.enumAfrica.data.repository.CohortRepository;
import com.enumAfrica.dto.request.CreateCohortRequest;
import com.enumAfrica.dto.response.CreatedCohortResponse;
import com.enumAfrica.exception.CohortAlreadyExistsException;
import com.enumAfrica.exception.ProgramTypeDoesNotExistException;
import com.enumAfrica.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
@AllArgsConstructor
public class CohortServiceImpl implements CohortService{
    private final CohortRepository cohortRepository;
    private final ProgramTypeService programTypeService;
    private final Mapper mapper;

    @Override
    public CreatedCohortResponse createCohort(CreateCohortRequest createCohortRequest) throws CohortAlreadyExistsException, IOException, ProgramTypeDoesNotExistException {
        Cohort foundCohort = cohortRepository.findCohortByName(createCohortRequest.getName());
        if (foundCohort == null){
            validateProgramType(createCohortRequest);
            Cohort newCohort = mapper.map(createCohortRequest);
            Cohort savedCohort = cohortRepository.save(newCohort);
            return mapper.map(savedCohort);

        }
        throw new CohortAlreadyExistsException("Cohort Already Exists");
    }

    private void validateProgramType(CreateCohortRequest createCohortRequest) throws ProgramTypeDoesNotExistException {
        ProgramType foundProgram = programTypeService.getAProgramType(createCohortRequest.getProgramType());
        if (foundProgram == null) throw new ProgramTypeDoesNotExistException("Program does not exist");
    }

    @Override
    public Cohort getCohort(String name) {
        return cohortRepository.findCohortByName(name);
    }

    @Override
    public void deleteAll() {
        cohortRepository.deleteAll();
    }

    @Override
    public List<Cohort> getAllCohorts() {
        return cohortRepository.findAll();
    }


}
