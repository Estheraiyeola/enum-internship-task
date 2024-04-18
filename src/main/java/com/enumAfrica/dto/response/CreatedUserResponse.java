package com.enumAfrica.dto.response;

import com.enumAfrica.data.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatedUserResponse {
    private String message;
    private User user;
}
