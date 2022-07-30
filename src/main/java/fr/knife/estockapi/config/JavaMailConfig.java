package fr.knife.estockapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * The configuration for mail
 */
@Configuration
public class JavaMailConfig {
    /**
     * The host
     */
    @Value("${spring.mail.host}")
    private String host;

    /**
     * The port
     */
    @Value("${spring.mail.port}")
    private Integer port;

    /**
     * The username
     */
    @Value("${spring.mail.username}")
    private String username;

    /**
     * The password
     */
    @Value("${spring.mail.password}")
    private String password;

    /**
     * The flag to indicate if SMTP auth is required or not
     */
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private Boolean auth;

    /**
     * The flag to indicate if TLS is required or not
     */
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private Boolean tls;

    /**
     * The flag to indicate if debug mode is activated or not
     */
    @Value("${spring.mail.properties.mail.debug}")
    private Boolean debug;

    /**
     * The bean to manage emails
     *
     * @return The created bean
     */
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);

        Properties props = mailSender.getJavaMailProperties();

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", this.auth);
        props.put("mail.smtp.starttls.enable", this.tls);
        props.put("mail.debug", this.debug);

        return mailSender;
    }
}
