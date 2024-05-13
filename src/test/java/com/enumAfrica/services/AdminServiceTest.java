package com.enumAfrica.services;

import com.enumAfrica.data.model.Role;
import com.enumAfrica.data.model.User;
import com.enumAfrica.dto.request.CreateCohortRequest;
import com.enumAfrica.dto.request.CreateProgramRequest;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.dto.request.InviteInstructorRequest;
import com.enumAfrica.dto.response.CreatedCohortResponse;
import com.enumAfrica.dto.response.CreatedProgramResponse;
import com.enumAfrica.dto.response.CreatedUserResponse;
import com.enumAfrica.dto.response.InvitedInstructorResponse;
import com.enumAfrica.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void testThatExistsByDefault() throws AdminNotFoundException {
        User foundAdmin = adminService.findAdminByFirstName("admin");
        assertNotNull(foundAdmin);
    }



}