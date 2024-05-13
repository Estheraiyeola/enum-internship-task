package com.enumAfrica.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticatedOrganizationResponse {
    private String accessToken;
    private String message;
}
