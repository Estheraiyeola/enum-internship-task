package com.enumAfrica.dto.response;

import com.enumAfrica.data.model.Instructor;
import com.enumAfrica.data.model.Learner;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AcceptedLearnerInviteResponse {
    private String message;
    private Learner learners;
}
