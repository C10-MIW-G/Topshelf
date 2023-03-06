package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.PantryUsersDTO;
import nl.miwgroningen.ch10.topshelf.mapper.UserDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import jakarta.mail.MessagingException;
import nl.miwgroningen.ch10.topshelf.email.EmailService;
import nl.miwgroningen.ch10.topshelf.passwordGenerator.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Controls the userRepository
 */

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PantryRepository pantryRepository;
    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService sendEmail;
    private final PasswordGenerator passwordGenerator = new PasswordGenerator();

    @Autowired
    public UserService(UserRepository userRepository, PantryRepository pantryRepository,
                       UserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder, EmailService sendEmail) {
        this.userRepository = userRepository;
        this.pantryRepository = pantryRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
        this.sendEmail = sendEmail;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public List<PantryUsersDTO> findUsersByPantryId(Long pantryId) {
        return pantryRepository.findUsersByPantryId(pantryId)
                .stream()
                .map(userDTOMapper)
                .toList();
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

    public void resetPassword(User user) throws MessagingException {
        String newPassword = passwordGenerator.generateSecureRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        sendEmail.sendMessage(user, "Reset password", "Your password reset was successful \n" +
                "Your new password = " + newPassword + "\n" +
                "It's strongly advised to change your password immediately.");
    }
}
