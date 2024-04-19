package com.enumAfrica.services;

import com.enumAfrica.data.model.Role;
import com.enumAfrica.dto.request.AuthenticateUserRequest;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.dto.response.AuthenticatedUserResponse;
import com.enumAfrica.dto.response.CreatedUserResponse;
import com.enumAfrica.exception.UserAlreadyExistsException;
import com.enumAfrica.exception.UserWithThisCredentialsDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @BeforeEach
    public void setUserService(){
        userService.deleteAll();
    }
    @Test
    public void testThatUserCanBeCreated() throws UserAlreadyExistsException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Esther");
        createUserRequest.setLastName("Aiyeola");
        createUserRequest.setEmail("estheraiyeola@yahoo.com");
        createUserRequest.setPassword("password");
        createUserRequest.setRole(Role.ADMIN);

        CreatedUserResponse createdUserResponse = userService.createUser(createUserRequest);
        assertThat(createdUserResponse.getUser().getFirstName(), is("Esther"));
    }
    @Test
    public void testThatInstructorsCanRegister() throws UserAlreadyExistsException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Tomide");
        createUserRequest.setLastName("Muliyu");
        createUserRequest.setEmail("tomide@gmail.com");
        createUserRequest.setPassword("password");
        createUserRequest.setRole(Role.INSTRUCTOR);

        CreatedUserResponse createdUserResponse = userService.createUser(createUserRequest);
        assertThat(createdUserResponse.getUser().getFirstName(), is("Tomide"));
    }
    @Test
    public void testThatUniqueUsersAreCreated() throws UserAlreadyExistsException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Esther");
        createUserRequest.setLastName("Aiyeola");
        createUserRequest.setEmail("estheraiyeola@yahoo.com");
        createUserRequest.setPassword("password");
        createUserRequest.setRole(Role.ADMIN);

        CreatedUserResponse createdUserResponse = userService.createUser(createUserRequest);
        assertThat(createdUserResponse.getUser().getFirstName(), is("Esther"));
        assertThrows(UserAlreadyExistsException.class, ()->{userService.createUser(createUserRequest);});
    }

    @Test
    public void testThatUserCanBeAuthenticated() throws UserAlreadyExistsException, UserWithThisCredentialsDoesNotExistException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Esther");
        createUserRequest.setLastName("Aiyeola");
        createUserRequest.setEmail("estheraiyeola@yahoo.com");
        createUserRequest.setPassword("password");
        createUserRequest.setRole(Role.ADMIN);

        CreatedUserResponse createdUserResponse = userService.createUser(createUserRequest);
        assertThat(createdUserResponse.getUser().getFirstName(), is("Esther"));

        AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
        authenticateUserRequest.setEmail("estheraiyeola@yahoo.com");
        authenticateUserRequest.setPassword("password");

        AuthenticatedUserResponse response = userService.authenticateUser(authenticateUserRequest);
        assertThat(response.getMessage(), is("Successfully Authenticated"));
    }


}