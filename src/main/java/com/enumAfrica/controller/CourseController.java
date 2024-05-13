package com.enumAfrica.controller;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.Organization;
import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.response.CreatedCourseResponse;
import com.enumAfrica.exception.CourseAlreadyExistsException;
import com.enumAfrica.exception.OrganizationDoesNotExistException;
import com.enumAfrica.services.CourseService;
import com.enumAfrica.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/course")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest createCourseRequest, @RequestHeader("Authorization") String accessToken){
        try{
            String[] token = accessToken.split(" ");
            List<String> decodedToken = userService.verifyToken(token[1]);
            if (decodedToken.get(1).equals("ADMIN")){
                CreatedCourseResponse response = courseService.createCourse(createCourseRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  catch (CourseAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
