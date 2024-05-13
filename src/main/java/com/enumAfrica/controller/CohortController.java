package com.enumAfrica.controller;

import com.enumAfrica.data.model.*;
import com.enumAfrica.dto.request.CreateCohortRequest;
import com.enumAfrica.dto.request.CreateProgramRequest;
import com.enumAfrica.dto.request.CreateUserRequest;
import com.enumAfrica.dto.response.CreatedCohortResponse;
import com.enumAfrica.dto.response.CreatedProgramResponse;
import com.enumAfrica.dto.response.CreatedUserResponse;
import com.enumAfrica.exception.*;
import com.enumAfrica.services.*;
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
    private final OrganizationService organizationService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCohort(@RequestBody CreateCohortRequest createCohortRequest, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ORGANIZATION")){
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
            if (decodedToken.get(1).equals("ORGANIZATION")){
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
            if (decodedToken.get(1).equals("ORGANIZATION")){
                Cohort response = cohortService.getCohort(cohortName);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/get-cohorts-in-organization")
    public ResponseEntity<?> getCohortsInOrganization(@RequestParam Long organizationId, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ORGANIZATION")){
                Organization organization = organizationService.findOrganizationById(organizationId);
                List<Cohort> response = cohortService.findCohortByOrganization(organization);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (OrganizationDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/get-courses-in-cohort")
    public ResponseEntity<?> getCoursesInCohort(@RequestParam Long cohortId, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ORGANIZATION")){
                List<Course> response = cohortService.getAllCoursesInA_Cohort(cohortId);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (CohortNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/get-learners-in-cohort")
    public ResponseEntity<?> getALlLearnersInCohort(@RequestParam Long cohortId, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ORGANIZATION")){
                List<Learner> response = cohortService.getAllLearnersIn_A_Cohort(cohortId);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (CohortNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/get-instructors-in-cohort")
    public ResponseEntity<?> getInstructorsInCohort(@RequestParam Long cohortId, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ORGANIZATION")){
                List<Instructor> response = cohortService.getAllInstructorsIn_A_Cohort(cohortId);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (CohortNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
