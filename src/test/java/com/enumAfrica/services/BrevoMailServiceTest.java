package com.enumAfrica.services;

import com.enumAfrica.data.model.Recipient;
import com.enumAfrica.data.model.Sender;
import com.enumAfrica.dto.request.SendMailRequest;
import com.enumAfrica.dto.response.SendMailResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class BrevoMailServiceTest {
    @Autowired
    private MailService mailService;
    @Test
    public void testThatBrevoCanSendMailToTheCustomer(){
        Sender sender = new Sender();
        sender.setName("Esther");
        sender.setEmail("ojamata.semicolon@gmail.com");

        Recipient recipient = new Recipient();
        recipient.setName("Aiyeola");
        recipient.setEmail("estheraiyeola@yahoo.com");

        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setRecipients(List.of(recipient));
        sendMailRequest.setSender(sender);
        sendMailRequest.setLink("ouejiwrpowkposkoptkesko.com");


        SendMailResponse mailResponse = mailService.createMessageAndSendEmail(sendMailRequest);
        assertThat(mailResponse.getStatusCode(), is(201));

    }

    @Test
    public void testThatTheInitiateOtp_AndSendingMailServiceWorks(){
        Sender sender = new Sender();
        sender.setName("Esther");
        sender.setEmail("ojamata.semicolon@gmail.com");

        Recipient recipient = new Recipient();
        recipient.setName("Aiyeola");
        recipient.setEmail("estheraiyeola@yahoo.com");

        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setRecipients(List.of(recipient));
        sendMailRequest.setSender(sender);
        sendMailRequest.setLink("ouejiwrpowkposkoptkesko.com");

        SendMailResponse mailResponse = mailService.createMessageAndSendEmail(sendMailRequest);
        assertThat(mailResponse.getStatusCode(), is(201));

    }


}

