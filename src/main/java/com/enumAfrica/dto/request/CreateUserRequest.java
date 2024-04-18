package com.enumAfrica.dto.request;

import com.enumAfrica.data.model.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
