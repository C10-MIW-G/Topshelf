package nl.miwgroningen.ch10.topshelf.service;

import jakarta.servlet.annotation.MultipartConfig;
import nl.miwgroningen.ch10.topshelf.dto.PantryUsersDTO;
import nl.miwgroningen.ch10.topshelf.email.EmailService;
import nl.miwgroningen.ch10.topshelf.exception.UserNotFoundException;
import nl.miwgroningen.ch10.topshelf.exception.UsernameNotFoundException;
import nl.miwgroningen.ch10.topshelf.mapper.UserDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.Role;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.passwordGenerator.PasswordGenerator;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private  UserDTOMapper userDTOMapper;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private  EmailService sendEmail;
    @Mock
    private  PasswordGenerator passwordGenerator = new PasswordGenerator();
    @Mock
    private  PantryService pantryService;

    @InjectMocks
    private UserService userService;

    @Test
    void findPantryUsersDTOByUsernameWithNoUser() {
        String username = "John Doe";

        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.findPantryUsersDTOByUsername(username));
    }
    @Test
    void findPantryUsersDTOByUsernameWithAUser() {
        String username = "John Doe";
        User user = new User();
        user.setUsername(username);
        PantryUsersDTO pantryUsersDTO = new PantryUsersDTO(1L, username, "test@email.com", Role.USER);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(userDTOMapper.apply(user)).thenReturn(pantryUsersDTO);

        assertEquals(pantryUsersDTO, userService.findPantryUsersDTOByUsername(username));
    }
}