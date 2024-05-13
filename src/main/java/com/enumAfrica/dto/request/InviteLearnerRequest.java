package com.enumAfrica.dto.request;

import com.enumAfrica.data.model.Recipient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InviteLearnerRequest {
    private Long cohortId;
    private List<Recipient> learnerEmail;
    private String link;
}
