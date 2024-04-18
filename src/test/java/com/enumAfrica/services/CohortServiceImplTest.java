package com.enumAfrica.services;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.dto.request.CreateCohortRequest;
import com.enumAfrica.dto.response.CreatedCohortResponse;
import com.enumAfrica.exception.CohortAlreadyExistsException;
import com.enumAfrica.exception.ProgramTypeDoesNotExistException;
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
    private CohortService cohortService;


    @BeforeEach
    public void setCohortService(){
        cohortService.deleteAll();
    }
    @Test
    public void testThatCohortCanBeCreated() throws CohortAlreadyExistsException, IOException, ProgramTypeDoesNotExistException {



        CreateCohortRequest createCohortRequest = new CreateCohortRequest();
        createCohortRequest.setName("Ace clan");
        createCohortRequest.setDescription("Cohort 17");
        createCohortRequest.setAvatar("yikkhgfddd");
        createCohortRequest.setProgramType("Java");

        CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);
        System.out.println(response.getCohort().getAvatar());
        assertThat(response.getCohort().getName(), is("Ace clan"));
    }
    @Test
    public void testThat_A_CohortCanBeGotten() throws CohortAlreadyExistsException, IOException, ProgramTypeDoesNotExistException {


        CreateCohortRequest createCohortRequest = new CreateCohortRequest();
        createCohortRequest.setName("Ace clan");
        createCohortRequest.setDescription("Cohort 17");
        createCohortRequest.setAvatar("photo");
        createCohortRequest.setProgramType("Java");

        CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);
        System.out.println(response.getCohort().getAvatar());
        assertThat(response.getCohort().getName(), is("Ace clan"));

        Cohort foundCohort = cohortService.getCohort("Ace clan");
        assertThat(foundCohort.getDescription(), is("Cohort 17"));
    }
    @Test
    public void testThat_All_CohortsCanBeGotten() throws CohortAlreadyExistsException, IOException, ProgramTypeDoesNotExistException {
        CreateCohortRequest createCohortRequest = new CreateCohortRequest();
        createCohortRequest.setName("Ace clan");
        createCohortRequest.setDescription("Cohort 17");
        createCohortRequest.setAvatar("photo");
        createCohortRequest.setProgramType("Java");

        CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);
        System.out.println(response.getCohort().getAvatar());
        assertThat(response.getCohort().getName(), is("Ace clan"));

        CreateCohortRequest createCohortRequest2 = new CreateCohortRequest();
        createCohortRequest2.setName("Elite");
        createCohortRequest2.setDescription("Cohort 15");
        createCohortRequest2.setAvatar("photo");
        createCohortRequest2.setProgramType("Java");

        CreatedCohortResponse response2 = cohortService.createCohort(createCohortRequest2);
        System.out.println(response2.getCohort().getAvatar());
        assertThat(response2.getCohort().getName(), is("Elite"));

        List<Cohort> foundCohorts = cohortService.getAllCohorts();
        assertThat(foundCohorts.size(), is(2));
    }
}