package ec.edu.ups.practicaMicroserviciosDocker.email.service;

import ec.edu.ups.practicaMicroserviciosDocker.email.EmailRequest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

@ApplicationScoped
public class EmailService {

    private final String primaryHost = "sandbox.smtp.mailtrap.io";
    private final String secondaryHost = "backup.smtp.mailtrap.io";

    public void sendEmail(EmailRequest emailRequest) throws MessagingException {
        try {
            sendWithRetry(emailRequest, primaryHost);
        } catch (MessagingException primaryException) {
            // En caso de fallo, intentar con el servidor secundario
            sendWithRetry(emailRequest, secondaryHost);
        }
    }

    private void sendWithRetry(EmailRequest emailRequest, String host) throws MessagingException {
        Session mailSession = createSession(host);

        Message message = new MimeMessage(mailSession);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRequest.getTo()));
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getText());

        Transport.send(message);
    }

    private Session createSession(String host) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "2525");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("898ea41fe71f1b", "f67c8ff6ddd672");
            }
        };

        return Session.getInstance(props, authenticator);
    }
}
