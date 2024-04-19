package com.enumAfrica.services;

import com.enumAfrica.dto.request.CreateCourseRequest;
import com.enumAfrica.dto.response.CreatedCourseResponse;
import com.enumAfrica.exception.CourseAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {
    @Autowired
    private CourseService courseService;
    @BeforeEach
    public void setCourseService(){
        courseService.deleteAll();
    }
    @Test
    public void testThatCourseCanBeCreated() throws CourseAlreadyExistsException {
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setName("Java");

        CreatedCourseResponse createdCourseResponse = courseService.createCourse(createCourseRequest);
        assertThat(createdCourseResponse.getCourse().getName(), is("Java"));

    }
    @Test
    public void testThatUniqueCoursesCanBeCreated() throws CourseAlreadyExistsException {
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setName("Java");

        CreatedCourseResponse createdCourseResponse = courseService.createCourse(createCourseRequest);
        assertThat(createdCourseResponse.getCourse().getName(), is("Java"));
        assertThrows(CourseAlreadyExistsException.class, ()->{courseService.createCourse(createCourseRequest);});

    }
}