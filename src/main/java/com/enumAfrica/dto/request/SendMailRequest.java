package com.enumAfrica.dto.request;

import com.enumAfrica.data.model.Recipient;
import com.enumAfrica.data.model.Sender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SendMailRequest {
    private Sender sender;
    @JsonProperty("to")
    private List<Recipient> recipients;
    private String subject;
    private String htmlContent;
    private String link;

}
