package com.enumAfrica.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendMailResponse {
    private int statusCode;
    private String messageId;
    private String otp;
}
