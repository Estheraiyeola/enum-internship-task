package com.enumAfrica.controller;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.ProgramType;
import com.enumAfrica.dto.request.CreateCohortRequest;
import com.enumAfrica.dto.request.CreateProgramRequest;
import com.enumAfrica.dto.response.CreatedCohortResponse;
import com.enumAfrica.dto.response.CreatedProgramResponse;
import com.enumAfrica.exception.CohortAlreadyExistsException;
import com.enumAfrica.exception.ProgramAlreadyExistsException;
import com.enumAfrica.exception.ProgramTypeDoesNotExistException;
import com.enumAfrica.services.ProgramTypeService;
import com.enumAfrica.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/program")
@AllArgsConstructor
public class ProgramController {
    private final UserService userService;
    private final ProgramTypeService programTypeService;

    @PostMapping("/create")
    public ResponseEntity<?> createProgram(@RequestBody CreateProgramRequest createProgramRequest, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ADMIN")){
                CreatedProgramResponse response = programTypeService.createProgram(createProgramRequest);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ProgramAlreadyExistsException  e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCohorts(@RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ADMIN")){
                List<ProgramType> response = programTypeService.getAllProgramType();
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCohort(@RequestParam String programName, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ADMIN")){
                ProgramType response = programTypeService.getAProgramType(programName);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
