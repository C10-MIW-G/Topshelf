package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import jakarta.mail.MessagingException;
import nl.miwgroningen.ch10.topshelf.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Controls the userRepository
 */

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService sendEmail;

    @Value("${resetPassword}")
    private String resetPassword;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService sendEmail) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sendEmail = sendEmail;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public boolean checkPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public boolean checkUserEmail(User user, String email) {
        return email.equals(user.getEmail());
    }

    public void resetPassword(User user) {
        String password = resetPassword;
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void sendResetPasswordMailToUser(User user) throws MessagingException {
        sendEmail.sendMessage(user, "Reset password",
                "Your password reset was successful \n" +
                        "Your new password = " + resetPassword + "\n" +
                        "It's strongly advised to change your password immediately.");
    }
}
