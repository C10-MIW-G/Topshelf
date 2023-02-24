package nl.miwgroningen.ch10.topshelf.email;

import jakarta.mail.MessagingException;
import nl.miwgroningen.ch10.topshelf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Send emails
 */

@Service
public class EmailService {

    @Lazy
    @Autowired
    private JavaMailSender emailSender;

    @Value("${emailUsername}")
    private String username;

    @Value("${emailServicePassword}")
    private String password;

    public void sendMessage(User receiver, String subject, String text) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(username);
        message.setTo(receiver.getEmail());
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);

        System.out.println("Mail sent successfully.");
    }

    public void sendMessageToUsers(User receiver, String subject, String text, List<String> listOfReceivers) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(receiver.getEmail());
        message.setSubject(subject);
        message.setText(text);

        if (listOfReceivers != null && listOfReceivers.size() > 0) {
            message.setCc(getMailAddressArray(listOfReceivers));
        }

        emailSender.send(message);
    }

    private String[] getMailAddressArray(List<String> mailAddressList) {
        String[] emailReceivers = new String[mailAddressList.size()];
        for (int i = 0; i < mailAddressList.size(); i++) {
            emailReceivers[i] = mailAddressList.get(i);
        }
        return emailReceivers;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return mailSender;
    }
}
