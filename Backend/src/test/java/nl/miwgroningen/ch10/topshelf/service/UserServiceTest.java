package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.PantryUsersDTO;
import nl.miwgroningen.ch10.topshelf.exception.UserNotFoundException;
import nl.miwgroningen.ch10.topshelf.mapper.UserDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.Role;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private  UserDTOMapper userDTOMapper;
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