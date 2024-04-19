package com.enumAfrica.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateOrganizationRequest {
    private String name;
    private String email;
}
