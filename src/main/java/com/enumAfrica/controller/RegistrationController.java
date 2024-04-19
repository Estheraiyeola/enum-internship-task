package com.enumAfrica.controller;

import com.enumAfrica.dto.request.AuthenticateUserRequest;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.dto.response.AuthenticatedUserResponse;
import com.enumAfrica.dto.response.CreatedUserResponse;
import com.enumAfrica.exception.UserAlreadyExistsException;
import com.enumAfrica.exception.UserWithThisCredentialsDoesNotExistException;
import com.enumAfrica.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest createUserRequest){
        try{
            CreatedUserResponse response = userService.createUser(createUserRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }  catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
