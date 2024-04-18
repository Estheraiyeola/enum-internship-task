package com.enumAfrica.controller;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.dto.request.CreateCohortRequest;
import com.enumAfrica.dto.request.CreateProgramRequest;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.dto.response.CreatedCohortResponse;
import com.enumAfrica.dto.response.CreatedProgramResponse;
import com.enumAfrica.dto.response.CreatedUserResponse;
import com.enumAfrica.exception.CohortAlreadyExistsException;
import com.enumAfrica.exception.ProgramAlreadyExistsException;
import com.enumAfrica.exception.ProgramTypeDoesNotExistException;
import com.enumAfrica.exception.UserAlreadyExistsException;
import com.enumAfrica.services.AdminService;
import com.enumAfrica.services.CohortService;
import com.enumAfrica.services.ProgramTypeService;
import com.enumAfrica.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cohort")
@AllArgsConstructor
public class CohortController {

    private final UserService userService;
    private final CohortService cohortService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCohort(@RequestBody CreateCohortRequest createCohortRequest, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ADMIN")){
                CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (CohortAlreadyExistsException | ProgramTypeDoesNotExistException | IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCohorts(@RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ADMIN")){
                List<Cohort> response = cohortService.getAllCohorts();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCohort(@RequestParam String cohortName, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ADMIN")){
                Cohort response = cohortService.getCohort(cohortName);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
