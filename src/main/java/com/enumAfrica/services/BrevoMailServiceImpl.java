package com.enumAfrica.services;

import com.enumAfrica.config.BrevoConfig;
import com.enumAfrica.dto.request.SendMailRequest;
import com.enumAfrica.dto.response.SendMailResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
@Service
@AllArgsConstructor
public class BrevoMailServiceImpl implements MailService {

    private final BrevoConfig brevoConfig;
    private final RestTemplate restTemplate;


    @Override
    public SendMailResponse createMessageAndSendEmail(SendMailRequest request){
        request.setHtmlContent("<!DOCTYPE html> <html> <body> <h1>You have been invited as an instructor</h1> <p>Please click on the link below to accept the invite.</p> <a>" + request.getLink() + "</a> </body> </html>");
        request.setSubject("You have been invited as an Instructor");
        return sendMail(request);
    }

    @Override
    public SendMailResponse sendMail(SendMailRequest request) {
        HttpHeaders headers = addRequestHeaders();
        RequestEntity<SendMailRequest> requestEntity = new RequestEntity<>(request, headers, POST, URI.create(brevoConfig.getBrevoMailServiceUrl()));
        ResponseEntity<SendMailResponse> mailResponse = restTemplate.postForEntity(brevoConfig.getBrevoMailServiceUrl(), requestEntity, SendMailResponse.class);
        return buildSendMailResponse(mailResponse);
    }

    @Override
    public SendMailResponse createMessageAndSendEmailToLearner(SendMailRequest request) {
        request.setHtmlContent("<!DOCTYPE html> <html> <body> <h1>You have been invited as a Learner</h1> <p>Please click on the link below to accept the invite.</p> <a>" + request.getLink() + "</a> </body> </html>");
        request.setSubject("You have been invited as an Instructor");
        return sendMail(request);
    }


    private HttpHeaders addRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(List.of(APPLICATION_JSON));
        headers.set("api-key", brevoConfig.getBrevoMailApi());
        return headers;
    }

    private SendMailResponse buildSendMailResponse(ResponseEntity<SendMailResponse> mailResponse) {
        HttpStatusCode statusCode = mailResponse.getStatusCode();
        SendMailResponse response = mailResponse.getBody();

        if (response == null) throw new RuntimeException("Error occurred when sending mail");
        response.setStatusCode(statusCode.value());
        return response;
    }

}

