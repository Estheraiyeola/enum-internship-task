package com.enumAfrica.services;

import com.enumAfrica.data.model.ProgramType;
import com.enumAfrica.data.repository.ProgramTypeRepository;
import com.enumAfrica.dto.request.CreateProgramRequest;
import com.enumAfrica.dto.response.CreatedProgramResponse;
import com.enumAfrica.exception.ProgramAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProgramTypeServiceImpl implements ProgramTypeService{
    private final ProgramTypeRepository programTypeRepository;
    @Override
    public CreatedProgramResponse createProgram(CreateProgramRequest createProgramRequest) throws ProgramAlreadyExistsException {
        ProgramType programType = programTypeRepository.findByName(createProgramRequest.getName());
        if (programType == null){
            ProgramType newProgram = new ProgramType();
            newProgram.setName(createProgramRequest.getName());
            ProgramType savedProgram = programTypeRepository.save(newProgram);

            CreatedProgramResponse createdProgramResponse = new CreatedProgramResponse();
            createdProgramResponse.setMessage("Program successfully created");
            createdProgramResponse.setProgramType(savedProgram);
            return createdProgramResponse;
        }
        throw new ProgramAlreadyExistsException("Program Already Exists");
    }

    @Override
    public void deleteAll() {
        programTypeRepository.deleteAll();
    }

    @Override
    public List<ProgramType> getAllProgramType() {
        return programTypeRepository.findAll();
    }

    @Override
    public ProgramType getAProgramType(String name) {
        return programTypeRepository.findByName(name);
    }
}
