package com.enumAfrica.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticatedUserResponse {
    private String message;
    private String  accessToken;
}
