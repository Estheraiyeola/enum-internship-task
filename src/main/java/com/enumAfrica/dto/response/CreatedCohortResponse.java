package com.enumAfrica.dto.response;

import com.enumAfrica.data.model.Cohort;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatedCohortResponse {
    private String message;
    private Cohort cohort;
}
