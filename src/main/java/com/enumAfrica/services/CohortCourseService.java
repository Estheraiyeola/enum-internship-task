package com.enumAfrica.services;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.dto.request.AddCourseToCohortRequest;
import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.response.AddedCourseToCohortResponse;
import com.enumAfrica.dto.response.CreatedCourseResponse;
import com.enumAfrica.exception.CohortNotFoundException;
import com.enumAfrica.exception.CourseAlreadyExistsException;
import com.enumAfrica.exception.CourseNotFoundException;
import jakarta.transaction.Transactional;

public interface CohortCourseService {
    CreatedCourseResponse createCourse(CreateCourseRequest createCourseRequest) throws CourseAlreadyExistsException, CohortNotFoundException;

    @Transactional
    void deleteAll();

    @Transactional
    AddedCourseToCohortResponse addCourse(AddCourseToCohortRequest addCourseToCohortRequest) throws CohortNotFoundException, CourseNotFoundException;

    Cohort findById(Long cohortId) throws CohortNotFoundException;
}
