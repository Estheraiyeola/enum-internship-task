package com.enumAfrica.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveInstructorRequest {
    private Long cohortId;
    private Long instructorId;
}
