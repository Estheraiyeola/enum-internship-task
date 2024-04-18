package com.enumAfrica.controller;

import com.enumAfrica.dto.request.AuthenticateUserRequest;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.dto.response.AuthenticatedUserResponse;
import com.enumAfrica.dto.response.CreatedUserResponse;
import com.enumAfrica.exception.UserAlreadyExistsException;
import com.enumAfrica.exception.UserWithThisCredentialsDoesNotExistException;
import com.enumAfrica.services.ProgramTypeService;
import com.enumAfrica.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticateUserRequest authenticateUserRequest){
        try{
            AuthenticatedUserResponse response = userService.authenticateUser(authenticateUserRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }  catch (UserWithThisCredentialsDoesNotExistException e) {
            return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
