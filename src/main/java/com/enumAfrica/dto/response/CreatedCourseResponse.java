package com.enumAfrica.dto.response;

import com.enumAfrica.data.model.Course;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatedCourseResponse {
    private String message;
    private Course course;
}
