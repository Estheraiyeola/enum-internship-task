package com.enumAfrica.services;

import com.enumAfrica.data.model.Course;
import com.enumAfrica.data.repository.CourseRepository;
import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.response.CreatedCourseResponse;
import com.enumAfrica.exception.CourseAlreadyExistsException;
import com.enumAfrica.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final Mapper mapper;
    @Override
    public CreatedCourseResponse createCourse(CreateCourseRequest createCourseRequest) throws CourseAlreadyExistsException {
        Course foundCourse = courseRepository.findCourseByName(createCourseRequest.getName());
        if (foundCourse == null){
            Course newCourse = mapper.map(createCourseRequest);
            Course savedCourse = courseRepository.save(newCourse);

            CreatedCourseResponse response = new CreatedCourseResponse();
            response.setCourse(savedCourse);
            response.setMessage("Course Created Successfully");
            return response;
        }
        throw new CourseAlreadyExistsException("Course Already Exists");
    }

    @Override
    public void deleteAll() {
        courseRepository.deleteAll();
    }


}
