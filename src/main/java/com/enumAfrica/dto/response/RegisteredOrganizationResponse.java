package com.enumAfrica.dto.response;

import com.enumAfrica.data.model.Organization;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisteredOrganizationResponse {
    private String message;
    private Organization organization;
}
