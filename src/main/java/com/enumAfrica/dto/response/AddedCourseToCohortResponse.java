package com.enumAfrica.dto.response;

import com.enumAfrica.data.model.Course;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class AddedCourseToCohortResponse {
    private String message;
    private Course course;
}
