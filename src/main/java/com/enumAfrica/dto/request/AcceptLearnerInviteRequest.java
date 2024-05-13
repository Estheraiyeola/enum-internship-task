package com.enumAfrica.dto.request;

import com.enumAfrica.data.model.Instructor;
import com.enumAfrica.data.model.Learner;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AcceptLearnerInviteRequest {
    private Learner learner;
    private Long cohortId;
}
