package com.enumAfrica.dto.response;

import com.enumAfrica.data.model.ProgramType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatedProgramResponse {
    private String message;
    private ProgramType programType;
}
