package com.enumAfrica.dto.request;

import com.enumAfrica.data.model.Instructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AcceptInviteRequest {
    private Long cohortId;
    private Instructor instructor;
}
