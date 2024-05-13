package com.enumAfrica.services;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.enumAfrica.data.model.Organization;
import com.enumAfrica.dto.request.AuthenticateOrganizationRequest;
import com.enumAfrica.dto.request.RegisterOrganizationRequest;
import com.enumAfrica.dto.response.AuthenticatedOrganizationResponse;
import com.enumAfrica.dto.response.RegisteredOrganizationResponse;
import com.enumAfrica.exception.OrganizationAlreadyExistsException;
import com.enumAfrica.exception.OrganizationDoesNotExistException;

import java.util.List;

public interface OrganizationService {
    RegisteredOrganizationResponse registerOrganization(RegisterOrganizationRequest registerOrganizationRequest) throws OrganizationAlreadyExistsException;

    void deleteAll();

    AuthenticatedOrganizationResponse authenticateOrganization(AuthenticateOrganizationRequest authenticateOrganizationRequest) throws OrganizationDoesNotExistException;

    List<String> verifyToken(String accessToken) throws JWTDecodeException;

    void deleteOrganization(Organization organization);

    List<Organization> getAllOrganizations();

    Organization findOrganizationById(Long organizationId) throws OrganizationDoesNotExistException;
}
