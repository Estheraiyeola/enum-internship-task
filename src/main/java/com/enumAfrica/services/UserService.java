package com.enumAfrica.services;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.enumAfrica.data.model.User;
import com.enumAfrica.dto.request.AuthenticateUserRequest;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.dto.response.AuthenticatedUserResponse;
import com.enumAfrica.dto.response.CreatedUserResponse;
import com.enumAfrica.exception.UserAlreadyExistsException;
import com.enumAfrica.exception.UserWithThisCredentialsDoesNotExistException;

import java.util.List;

public interface UserService {
    CreatedUserResponse createUser(CreateUserRequest createUserRequest) throws UserAlreadyExistsException;

    void deleteAll();

    User findByFirstName(String admin);

    boolean adminExists();

    AuthenticatedUserResponse authenticateUser(AuthenticateUserRequest authenticateUserRequest) throws UserWithThisCredentialsDoesNotExistException;

    List<String> verifyToken(String accessToken) throws JWTDecodeException;
}
