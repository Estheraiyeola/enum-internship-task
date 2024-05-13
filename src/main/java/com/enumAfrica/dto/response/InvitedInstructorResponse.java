package com.enumAfrica.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvitedInstructorResponse {
    private String message;
    private SendMailResponse sendMailResponse;
}
