package com.enumAfrica.dto.request;

import com.enumAfrica.data.model.Course;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCourseToCohortRequest {
    private Long courseId;
    private Long cohortId;
}
