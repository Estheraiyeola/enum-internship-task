package com.enumAfrica.services;

import com.enumAfrica.data.model.ProgramType;
import com.enumAfrica.dto.request.CreateProgramRequest;
import com.enumAfrica.dto.response.CreatedProgramResponse;
import com.enumAfrica.exception.ProgramAlreadyExistsException;

import java.util.List;

public interface ProgramTypeService {
    CreatedProgramResponse createProgram(CreateProgramRequest createProgramRequest) throws ProgramAlreadyExistsException;

    void deleteAll();

    List<ProgramType> getAllProgramType();

    ProgramType getAProgramType(String java);
}
