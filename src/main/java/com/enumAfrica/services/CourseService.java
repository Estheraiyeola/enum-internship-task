package com.enumAfrica.services;

import com.enumAfrica.data.model.Course;
import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.response.CreatedCourseResponse;
import com.enumAfrica.exception.CourseAlreadyExistsException;
import com.enumAfrica.exception.CourseNotFoundException;

import java.util.List;

public interface CourseService {
    CreatedCourseResponse createCourse(CreateCourseRequest createCourseRequest) throws CourseAlreadyExistsException;

    void deleteAll();

    Course findById(Long courseId) throws CourseNotFoundException;

    void save(Course course);

    void deleteCourses(List<Course> courses);
}
