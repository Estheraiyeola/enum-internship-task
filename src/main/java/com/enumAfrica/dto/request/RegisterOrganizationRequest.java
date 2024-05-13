package com.enumAfrica.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterOrganizationRequest {
    private String name;
    private String cac;
    private String email;
    private String password;
}
