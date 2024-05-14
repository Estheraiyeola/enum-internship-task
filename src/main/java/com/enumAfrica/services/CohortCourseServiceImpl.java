package com.enumAfrica.services;

import com.enumAfrica.data.model.Cohort;
import com.enumAfrica.data.model.Course;
import com.enumAfrica.dto.request.AddCourseToCohortRequest;
import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.response.AddedCourseToCohortResponse;
import com.enumAfrica.dto.response.CreatedCourseResponse;
import com.enumAfrica.exception.CohortNotFoundException;
import com.enumAfrica.exception.CourseAlreadyExistsException;
import com.enumAfrica.exception.CourseNotFoundException;
import com.enumAfrica.utils.Mapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CohortCourseServiceImpl implements CohortCourseService{

    private final CohortService cohortService;
    private final CourseService courseService;
    private final Mapper mapper;
    @Override
    public CreatedCourseResponse createCourse(CreateCourseRequest createCourseRequest) throws CourseAlreadyExistsException, CohortNotFoundException {
        Cohort cohort = cohortService.findById(createCourseRequest.getCohortId());
        Course foundCourse = courseService.findCourseByNameAndCohort(createCourseRequest.getName(), cohort);
        if (foundCourse == null){
            Course newCourse = mapper.map(createCourseRequest);
            Cohort foundCohort = findById(createCourseRequest.getCohortId());
            newCourse.setCohort(foundCohort);
            Course savedCourse = courseService.save(newCourse);

            CreatedCourseResponse response = new CreatedCourseResponse();
            response.setCourse(savedCourse);
            response.setMessage("Course Created Successfully");
            return response;
        }
        throw new CourseAlreadyExistsException("Course Already Exists");
    }

    @Override
    @Transactional
    public void deleteAll() {
        for (Cohort cohort:cohortService.findAll()) {
            List<Course> courses = cohort.getCourses();
            courseService.deleteCourses(courses);
            cohortService.delete(cohort);
        }
    }

    @Override
    @Transactional
    public AddedCourseToCohortResponse addCourse(AddCourseToCohortRequest addCourseToCohortRequest) throws CohortNotFoundException, CourseNotFoundException {
        Cohort cohort = cohortService.findById(addCourseToCohortRequest.getCohortId());
        Course course = courseService.findById(addCourseToCohortRequest.getCourseId());
        AddedCourseToCohortResponse addedCourseToCohortResponse = new AddedCourseToCohortResponse();

        if (cohort != null){
            List<Course> courses = cohort.getCourses();
            courses.add(course);
            cohort.setCourses(courses);
            cohortService.save(cohort);
            course.setCohort(cohort);
            courseService.save(course);

            addedCourseToCohortResponse.setCourse(course);
            addedCourseToCohortResponse.setMessage("Course added successfully");
            return addedCourseToCohortResponse;
        }
        addedCourseToCohortResponse.setMessage("Course not added");
        return addedCourseToCohortResponse;
    }

    @Override
    public Cohort findById(Long cohortId) throws CohortNotFoundException {
        return cohortService.findById(cohortId);
    }
}
