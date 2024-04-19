package com.enumAfrica.services;

import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.response.CreatedCourseResponse;
import com.enumAfrica.exception.CourseAlreadyExistsException;

public interface CourseService {
    CreatedCourseResponse createCourse(CreateCourseRequest createCourseRequest) throws CourseAlreadyExistsException;

    void deleteAll();
}
