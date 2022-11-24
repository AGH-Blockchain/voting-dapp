package pl.edu.agh.blockchain.offchainservice.service;

import com.sendgrid.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EMailServiceImpl implements EMailService {

    @Override
    public void sendEmail(String email, String token) throws IOException {
        Email from = new Email("blockchain@agh.edu.pl");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email(email);
        Content content = new Content("text/plain", token);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        sg.api(request);
    }
}
