package com.enumAfrica.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignCourseToInstructorRequest {
    private Long instructorId;
    private Long courseId;
}
