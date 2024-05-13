package com.enumAfrica.services;

import com.enumAfrica.data.model.Course;
import com.enumAfrica.data.model.Instructor;
import com.enumAfrica.data.model.User;
import com.enumAfrica.data.repository.InstructorRepository;
import com.enumAfrica.dto.request.AssignCourseToInstructorRequest;
import com.enumAfrica.dto.response.AssignedCourseToInstructorResponse;
import com.enumAfrica.exception.CourseNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final UserService userService;
    private final CourseService courseService;
    private final InstructorRepository instructorRepository;
    @Override
    @Transactional
    public AssignedCourseToInstructorResponse assignCourseToCohort(AssignCourseToInstructorRequest assignCourseToInstructorRequest) throws CourseNotFoundException {
        User foundInstructor = userService.findById(assignCourseToInstructorRequest.getInstructorId());
        Course foundCourse = courseService.findById(assignCourseToInstructorRequest.getCourseId());
        Instructor castedInstructor = (Instructor) foundInstructor;
        List<Course> courses = castedInstructor.getCourses();
        courses.add(foundCourse);

        AssignedCourseToInstructorResponse assignedCourseToInstructorResponse = new AssignedCourseToInstructorResponse();
        assignedCourseToInstructorResponse.setInstructor((Instructor) foundInstructor);
        assignedCourseToInstructorResponse.setMessage("Course has been assigned to instructor");
        return assignedCourseToInstructorResponse;
    }
    @Override
    public List<Instructor> getInstructorsByOrganization(Long organizationId){
        return instructorRepository.findInstructorsByOrganizationId(organizationId);
    }
}
