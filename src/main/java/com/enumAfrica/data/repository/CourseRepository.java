package com.enumAfrica.data.repository;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseByNameAndCohortId (String name, Long cohortId);
}
