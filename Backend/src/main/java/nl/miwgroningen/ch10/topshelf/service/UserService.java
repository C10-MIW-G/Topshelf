package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.PantryUsersDTO;
import nl.miwgroningen.ch10.topshelf.exception.UserNotFoundException;
import nl.miwgroningen.ch10.topshelf.mapper.UserDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import jakarta.mail.MessagingException;
import nl.miwgroningen.ch10.topshelf.email.EmailService;
import nl.miwgroningen.ch10.topshelf.passwordGenerator.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Controls the userRepository
 */

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService sendEmail;
    private final PasswordGenerator passwordGenerator = new PasswordGenerator();
    private final PantryService pantryService;

    @Autowired
    @Lazy
    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper, PasswordEncoder passwordEncoder,
                       EmailService sendEmail, PantryService pantryService) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
        this.sendEmail = sendEmail;
        this.pantryService = pantryService;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public PantryUsersDTO findPantryUsersDTOByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userDTOMapper)
                .orElseThrow(() -> new UserNotFoundException("User with username: " +
                        username + " was not found!"));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public List<PantryUsersDTO> findAdminsByPantryId(Long pantryId) {
        List<User> pantryAdmins = userRepository.findAllByAdminPantriesPantryId(pantryId);
        return pantryAdmins
                .stream()
                .map(userDTOMapper)
                .toList();
    }

    public List<PantryUsersDTO> findUsersByPantryId(Long pantryId) {
        List<User> pantryUsers = userRepository.findAllByUserPantriesPantryId(pantryId);
        return pantryUsers
                .stream()
                .map(userDTOMapper)
                .toList();
    }

    public List<PantryUsersDTO> checkIfUserIsAdmin(Long pantryId) {
        List<PantryUsersDTO> pantryAdmins = findAdminsByPantryId(pantryId);
        List<PantryUsersDTO> pantryUsers = findUsersByPantryId(pantryId);

        return pantryUsers
                .stream()
                .filter(u -> !pantryAdmins.contains(u))
                .collect(Collectors.toList());
    }

    public List<PantryUsersDTO> checkIfUserIsPantryAdmin(User user, Long pantryId) {
        List<PantryUsersDTO> pantryAdmins = findAdminsByPantryId(pantryId);

        return pantryAdmins
                .stream()
                .filter(u -> user.getUsername().equals(u.username()))
                .collect(Collectors.toList());
    }

    public boolean checkPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public boolean checkUserEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void resetPassword(User user) throws MessagingException {
        String newPassword = passwordGenerator.generateSecureRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        sendEmail.sendMessage(user, "Reset password", "Hello " + user + "\n\n" +
                "Your password reset was successful \n" +
                "Your new password = " + newPassword + "\n" +
                "It's strongly advised to change your password immediately. \n\n With kind regards, \n\n Topshelf");
    }

    public void inviteUserToPantry(User user, Pantry pantryId) throws MessagingException {
        if (pantryService.checkIfUserIsPartOfPantry(user.getUsername(), pantryId)) {
            pantryService.addUserToPantry(user.getUsername(), pantryId);
            sendEmail.sendMessage(user, "Invite to pantry: " + pantryId.getName(),
                    "Hello " + user + "\n\n" + "Great news: you've been invited to pantry: " + pantryId.getName()
                            + "\n" + "Log in and check out your new pantry: http://localhost:4200/ " +
                            "\n\n With kind regards, \n\n Topshelf" );
        } else {
            throw new UserNotFoundException("User is already part of the pantry");
        }
    }
}
