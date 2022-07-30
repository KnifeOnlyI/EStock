package fr.knife.estockapi.service;

import fr.knife.estockapi.exception.InternalServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * The service to manage emails
 */
@Service
public class EmailService {
    /**
     * The mail sender
     */
    private final JavaMailSender mailSender;

    /**
     * The app name
     */
    @Value("${app.name}")
    private String appName;

    /**
     * The sender email
     */
    @Value("${app.mail.sender}")
    private String sender;

    /**
     * Create a new service to manage emails
     *
     * @param mailSender The mail sender
     */
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Send a message to the specified receiver
     *
     * @param to      The receiver
     * @param subject The subject
     * @param content The content
     */
    public void send(String to, String subject, String content) {
        MimeMessage message = this.mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.sender, this.appName);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new InternalServerException(e.getMessage());
        }

        this.mailSender.send(message);
    }
}
