package com.enumAfrica.services;

import com.enumAfrica.dto.request.SendMailRequest;
import com.enumAfrica.dto.response.SendMailResponse;

public interface MailService {
    SendMailResponse createMessageAndSendEmail(SendMailRequest request);



    SendMailResponse sendMail(SendMailRequest request);


    SendMailResponse createMessageAndSendEmailToLearner(SendMailRequest sendMailRequest);
}
