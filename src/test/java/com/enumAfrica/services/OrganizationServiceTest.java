package com.enumAfrica.services;

import com.enumAfrica.dto.request.AuthenticateOrganizationRequest;
import com.enumAfrica.dto.request.RegisterOrganizationRequest;
import com.enumAfrica.dto.response.AuthenticatedOrganizationResponse;
import com.enumAfrica.dto.response.RegisteredOrganizationResponse;
import com.enumAfrica.exception.OrganizationAlreadyExistsException;
import com.enumAfrica.exception.OrganizationDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrganizationServiceTest {
    @Autowired
    private OrganizationService organizationService;

    @BeforeEach
    public void setOrganizationService(){
        organizationService.deleteAll();
    }
    @Test
    public void testThatOrganizationCanRegister() throws OrganizationAlreadyExistsException {
        RegisterOrganizationRequest registerOrganizationRequest = new RegisterOrganizationRequest();
        registerOrganizationRequest.setName("Semicolon Africa");
        registerOrganizationRequest.setCac("1234Semicolon");
        registerOrganizationRequest.setEmail("semicolonafrica.com");
        registerOrganizationRequest.setPassword("password");

        RegisteredOrganizationResponse response = organizationService.registerOrganization(registerOrganizationRequest);
        assertThat(response.getMessage(), is("Organization Registered Successfully"));
    }
    @Test
    public void testThatUniqueOrganizationsCanRegister() throws OrganizationAlreadyExistsException {
        RegisterOrganizationRequest registerOrganizationRequest = new RegisterOrganizationRequest();
        registerOrganizationRequest.setName("Semicolon Africa");
        registerOrganizationRequest.setCac("1234Semicolon");
        registerOrganizationRequest.setEmail("semicolonafrica.com");
        registerOrganizationRequest.setPassword("password");

        RegisteredOrganizationResponse response = organizationService.registerOrganization(registerOrganizationRequest);
        assertThat(response.getMessage(), is("Organization Registered Successfully"));
        assertThrows(OrganizationAlreadyExistsException.class, ()->organizationService.registerOrganization(registerOrganizationRequest));
    }

    @Test
    public void testThatOrganizationCanAuthenticated() throws OrganizationAlreadyExistsException, OrganizationDoesNotExistException {
        RegisterOrganizationRequest registerOrganizationRequest = new RegisterOrganizationRequest();
        registerOrganizationRequest.setName("Semicolon Africa");
        registerOrganizationRequest.setCac("1234Semicolon");
        registerOrganizationRequest.setEmail("semicolonafrica.com");
        registerOrganizationRequest.setPassword("password");

        RegisteredOrganizationResponse response = organizationService.registerOrganization(registerOrganizationRequest);
        assertThat(response.getMessage(), is("Organization Registered Successfully"));

        AuthenticateOrganizationRequest authenticateOrganizationRequest = new AuthenticateOrganizationRequest();
        authenticateOrganizationRequest.setEmail("semicolonafrica.com");
        authenticateOrganizationRequest.setPassword("password");

        AuthenticatedOrganizationResponse authenticatedOrganizationResponse = organizationService.authenticateOrganization(authenticateOrganizationRequest);
        assertThat(authenticatedOrganizationResponse.getMessage(), is("Organization authenticated successfully"));
    }

}