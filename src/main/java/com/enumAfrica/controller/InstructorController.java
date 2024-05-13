package com.enumAfrica.controller;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.Instructor;
import com.enumAfrica.data.model.Organization;
import com.enumAfrica.dto.request.AcceptInviteRequest;
import com.enumAfrica.dto.request.AssignCourseToInstructorRequest;
import com.enumAfrica.dto.request.InviteInstructorRequest;
import com.enumAfrica.dto.request.RemoveInstructorRequest;
import com.enumAfrica.dto.response.AcceptedInstructorInviteResponse;
import com.enumAfrica.dto.response.AssignedCourseToInstructorResponse;
import com.enumAfrica.dto.response.InvitedInstructorResponse;
import com.enumAfrica.dto.response.RemovedInstructorResponse;
import com.enumAfrica.exception.CohortNotFoundException;
import com.enumAfrica.exception.CourseNotFoundException;
import com.enumAfrica.exception.OrganizationDoesNotExistException;
import com.enumAfrica.exception.UserWithThisCredentialsDoesNotExistException;
import com.enumAfrica.services.CohortService;
import com.enumAfrica.services.CohortUserService;
import com.enumAfrica.services.InstructorService;
import com.enumAfrica.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/instructor")
@AllArgsConstructor
public class InstructorController {
    private final CohortService cohortService;
    private final UserService userService;
    private final InstructorService instructorService;
    private final CohortUserService cohortUserService;
    @PostMapping("/invite")
    public ResponseEntity<?> inviteInstructor(@RequestBody InviteInstructorRequest inviteInstructorRequest, @RequestHeader("Authorization") String accessToken){
        String[] token = accessToken.split(" ");
        List<String> decodedToken = userService.verifyToken(token[1]);
        if (decodedToken.get(1).equals("ADMIN")){
            InvitedInstructorResponse response = cohortService.inviteInstructorToCohort(inviteInstructorRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/accept-invite")
    public ResponseEntity<?> acceptInvite(@RequestBody AcceptInviteRequest acceptInviteRequest, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("INSTRUCTOR")){
                AcceptedInstructorInviteResponse response = cohortService.acceptInstructorInvite(acceptInviteRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (CohortNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeInstructor(@RequestBody RemoveInstructorRequest removeInstructorRequest, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ADMIN")){
                RemovedInstructorResponse response = cohortUserService.removeInstructorFromCohort(removeInstructorRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (CohortNotFoundException | UserWithThisCredentialsDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllInstructorsInAnOrganization(@RequestParam Long organizationId, @RequestHeader("Authorization") String accessToken){
        String[] token = accessToken.split(" ");
        List<String> decodedToken = userService.verifyToken(token[1]);
        if (decodedToken.get(1).equals("ADMIN")){
            List<Instructor> response = instructorService.getInstructorsByOrganization(organizationId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/assign-course")
    public ResponseEntity<?> assignCourse(@RequestBody AssignCourseToInstructorRequest assignCourseToInstructorRequest, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ADMIN")){
                AssignedCourseToInstructorResponse response = instructorService.assignCourseToCohort(assignCourseToInstructorRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (CourseNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
