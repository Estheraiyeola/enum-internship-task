package com.enumAfrica.dto.response;

import com.enumAfrica.data.model.Instructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AcceptedInstructorInviteResponse {
    private String message;
    private Instructor instructor;
}
