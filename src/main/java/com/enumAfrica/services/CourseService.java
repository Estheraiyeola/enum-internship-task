package com.enumAfrica.services;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.Course;
import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.response.CreatedCourseResponse;
import com.enumAfrica.exception.CohortNotFoundException;
import com.enumAfrica.exception.CourseAlreadyExistsException;
import com.enumAfrica.exception.CourseNotFoundException;

import java.util.List;

public interface CourseService {

    void deleteAll();

    Course findById(Long courseId) throws CourseNotFoundException;

    Course save(Course course);

    void deleteCourses(List<Course> courses);

    Course findCourseByNameAndCohort(String name, Long cohortId);

    List<Course> findAll();

    Course findCourseByName(String name);
}
