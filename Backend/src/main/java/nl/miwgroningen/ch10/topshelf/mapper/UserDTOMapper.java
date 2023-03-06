package nl.miwgroningen.ch10.topshelf.mapper;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.dto.PantryUsersDTO;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.service.UserService;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * This class makes it possible to make use of DTO's
 */

@Service
@RequiredArgsConstructor
public class UserDTOMapper implements Function<User, PantryUsersDTO> {

    private final UserService userService;

    @Override
    public PantryUsersDTO apply(User user) {
        return new PantryUsersDTO(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }

    public User toUser(PantryUsersDTO userDTO) {
        return new User(
                userDTO.userId(),
                userDTO.username(),
                userService.findUserByUsername(userDTO.username()).getEmail(),
                userService.findUserByUsername(userDTO.username()).getPassword(),
                userDTO.role()
        );
    }
}
