package com.enumAfrica.controller;

import com.enumAfrica.data.model.Instructor;
import com.enumAfrica.data.model.Learner;
import com.enumAfrica.dto.request.*;
import com.enumAfrica.dto.response.*;
import com.enumAfrica.exception.CohortNotFoundException;
import com.enumAfrica.exception.CourseNotFoundException;
import com.enumAfrica.exception.UserWithThisCredentialsDoesNotExistException;
import com.enumAfrica.services.CohortService;
import com.enumAfrica.services.InstructorService;
import com.enumAfrica.services.LearnerService;
import com.enumAfrica.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/learner")
@AllArgsConstructor
public class LearnerController {
    private final CohortService cohortService;
    private final UserService userService;
    private final LearnerService learnerService;
    @PostMapping("/invite")
    public ResponseEntity<?> inviteLearner(@RequestBody InviteLearnerRequest inviteLearnerRequest, @RequestHeader("Authorization") String accessToken){
        String[] token = accessToken.split(" ");
        List<String> decodedToken = userService.verifyToken(token[1]);
        if (decodedToken.get(1).equals("ADMIN")){
            InvitedLearnerResponse response = cohortService.inviteLearnerToCohort(inviteLearnerRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/accept-invite")
    public ResponseEntity<?> acceptInvite(@RequestBody AcceptLearnerInviteRequest acceptInviteRequest, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("LEARNER")){
                AcceptedLearnerInviteResponse response = cohortService.acceptLearnerInvite(acceptInviteRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (CohortNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeLearner(@RequestBody RemoveInstructorRequest removeInstructorRequest, @RequestHeader("Authorization") String accessToken){
        return null;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllLearnersInAnOrganization(@RequestParam Long organizationId, @RequestHeader("Authorization") String accessToken){
        String[] token = accessToken.split(" ");
        List<String> decodedToken = userService.verifyToken(token[1]);
        if (decodedToken.get(1).equals("ADMIN")){
            List<Learner> response = learnerService.getLearnersByOrganization(organizationId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
