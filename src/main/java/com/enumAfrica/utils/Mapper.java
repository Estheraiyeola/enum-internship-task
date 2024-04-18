package com.enumAfrica.utils;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.User;
import com.enumAfrica.dto.request.CreateCohortRequest;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.dto.response.CreatedCohortResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class Mapper {
    public Cohort map(CreateCohortRequest createCohortRequest) throws IOException {
        Cohort newCohort = new Cohort();
        newCohort.setAvatar(createCohortRequest.getAvatar());
        newCohort.setDescription(createCohortRequest.getDescription());
        newCohort.setName(createCohortRequest.getName());
        newCohort.setStartDate(createCohortRequest.getStartDate());
        newCohort.setEndDate(createCohortRequest.getEndDate());
        newCohort.setProgramType(createCohortRequest.getProgramType());
        return newCohort;
    }


    public CreatedCohortResponse map(Cohort savedCohort) {
        CreatedCohortResponse createdCohortResponse = new CreatedCohortResponse();
        createdCohortResponse.setCohort(savedCohort);
        createdCohortResponse.setMessage("Cohort Created Successfully");
        return createdCohortResponse;
    }
    public User map(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setEmail(createUserRequest.getEmail());
        String encodedPassword = getEncryptedPassword(createUserRequest);
        user.setPassword(encodedPassword);
        user.setRole(createUserRequest.getRole());
        return user;
    }
    private static String getEncryptedPassword(CreateUserRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(request.getPassword());
    }

}
