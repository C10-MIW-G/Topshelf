package nl.miwgroningen.ch10.topshelf.mapper;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.dto.PantryUsersDTO;
import nl.miwgroningen.ch10.topshelf.model.User;
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

    @Override
    public PantryUsersDTO apply(User user) {
        return new PantryUsersDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}
