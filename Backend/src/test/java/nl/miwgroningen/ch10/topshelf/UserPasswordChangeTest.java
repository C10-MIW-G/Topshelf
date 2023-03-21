package nl.miwgroningen.ch10.topshelf;

import nl.miwgroningen.ch10.topshelf.email.EmailService;
import nl.miwgroningen.ch10.topshelf.mapper.UserDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.Role;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import nl.miwgroningen.ch10.topshelf.service.PantryService;
import nl.miwgroningen.ch10.topshelf.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

/**
 * Auteur Jessica Schouten.
 * <p>
 * Tests functions for UserService.
 */

@ExtendWith(MockitoExtension.class)
public class UserPasswordChangeTest {

    @Mock
    UserRepository userRepository;
    @Mock
    UserDTOMapper userDTOMapper;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    PantryService pantryService;
    @Mock
    EmailService emailService;

    private UserService userService;

    @BeforeEach
    public void UserTest() {
        userService = new UserService(userRepository, userDTOMapper, passwordEncoder, emailService, pantryService);
    }

    @Test
    @DisplayName("Test if password is updated")
    void testIfPasswordIsUpdated() {
        User user1 = new User(-3L, "User 1", "User1@pantry.nl", "password3", Role.USER);
        when(userRepository.save(user1)).thenReturn(user1);
        when(passwordEncoder.encode("ChangedPassword")).thenReturn("ChangedPassword");

        userService.changeUserPassword(user1, "ChangedPassword");
        Assertions.assertEquals("ChangedPassword", user1.getPassword());
    }
}