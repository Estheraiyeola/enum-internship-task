package com.enumAfrica.services;

import com.enumAfrica.data.model.Instructor;
import com.enumAfrica.dto.request.AssignCourseToInstructorRequest;
import com.enumAfrica.dto.response.AssignedCourseToInstructorResponse;
import com.enumAfrica.exception.CourseNotFoundException;

import java.util.List;

public interface InstructorService {
    AssignedCourseToInstructorResponse assignCourseToCohort(AssignCourseToInstructorRequest assignCourseToInstructorRequest) throws CourseNotFoundException;

    List<Instructor> getInstructorsByOrganization(Long organizationId);
}
