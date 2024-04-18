package com.enumAfrica.services;

import com.enumAfrica.data.model.ProgramType;
import com.enumAfrica.dto.request.CreateProgramRequest;
import com.enumAfrica.dto.response.CreatedProgramResponse;
import com.enumAfrica.exception.ProgramAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class ProgramTypeServiceTest {
    @Autowired
    private ProgramTypeService programTypeService;
    @BeforeEach
    public void setProgramTypeService(){
        programTypeService.deleteAll();
    }
    @Test
    public void testThatProgramTypeCanBe_Created() throws ProgramAlreadyExistsException {
        CreateProgramRequest createProgramRequest = new CreateProgramRequest();
        createProgramRequest.setName("Java");

        CreatedProgramResponse createdProgramResponse = programTypeService.createProgram(createProgramRequest);

        assertThat(createdProgramResponse.getProgramType().getName(), is("Java"));
    }

    @Test
    public void testThat_All_ProgramTypesCanBe_Gotten() throws ProgramAlreadyExistsException {
        CreateProgramRequest createProgramRequest = new CreateProgramRequest();
        createProgramRequest.setName("Java");

        CreatedProgramResponse createdProgramResponse = programTypeService.createProgram(createProgramRequest);

        assertThat(createdProgramResponse.getProgramType().getName(), is("Java"));

        CreateProgramRequest createProgramRequest2 = new CreateProgramRequest();
        createProgramRequest2.setName("Python");

        CreatedProgramResponse createdProgramResponse2 = programTypeService.createProgram(createProgramRequest2);

        assertThat(createdProgramResponse2.getProgramType().getName(), is("Python"));

        List<ProgramType> programTypeList = programTypeService.getAllProgramType();
        assertThat(programTypeList.size(), is(2));

    }

    @Test
    public void testThat_A_ProgramTypeCanBe_Gotten() throws ProgramAlreadyExistsException {
        CreateProgramRequest createProgramRequest = new CreateProgramRequest();
        createProgramRequest.setName("Java");

        CreatedProgramResponse createdProgramResponse = programTypeService.createProgram(createProgramRequest);

        assertThat(createdProgramResponse.getProgramType().getName(), is("Java"));

        CreateProgramRequest createProgramRequest2 = new CreateProgramRequest();
        createProgramRequest2.setName("Python");

        CreatedProgramResponse createdProgramResponse2 = programTypeService.createProgram(createProgramRequest2);

        assertThat(createdProgramResponse2.getProgramType().getName(), is("Python"));

        ProgramType programType = programTypeService.getAProgramType("Java");
        assertThat(programType.getName(), is("Java"));

    }
}
