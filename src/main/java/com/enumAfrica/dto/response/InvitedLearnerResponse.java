package com.enumAfrica.dto.response;

import com.enumAfrica.data.model.Learner;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvitedLearnerResponse {
    private String message;
    private SendMailResponse sendMailResponse;
}
