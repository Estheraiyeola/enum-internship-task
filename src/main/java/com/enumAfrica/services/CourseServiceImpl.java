package com.enumAfrica.services;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.Course;
import com.enumAfrica.data.repository.CourseRepository;
import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.response.CreatedCourseResponse;
import com.enumAfrica.exception.CohortNotFoundException;
import com.enumAfrica.exception.CourseAlreadyExistsException;
import com.enumAfrica.exception.CourseNotFoundException;
import com.enumAfrica.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;


    @Override
    public void deleteAll() {
        courseRepository.deleteAll();
    }

    @Override
    public Course findById(Long courseId) throws CourseNotFoundException {
        return courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    @Override
    public Course save(Course course) {
        courseRepository.save(course);
        return course;
    }

    @Override
    public void deleteCourses(List<Course> courses) {
        courseRepository.deleteAll(courses);
    }

    @Override
    public Course findCourseByNameAndCohort(String name, Long cohortId) {
        return courseRepository.findCourseByNameAndCohortId(name, cohortId);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }


}
