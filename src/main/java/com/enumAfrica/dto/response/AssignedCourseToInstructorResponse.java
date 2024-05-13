package com.enumAfrica.dto.response;

import com.enumAfrica.data.model.Instructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignedCourseToInstructorResponse {
    private Instructor instructor;
    private String message;
}
