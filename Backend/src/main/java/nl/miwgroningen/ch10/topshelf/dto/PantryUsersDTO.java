package nl.miwgroningen.ch10.topshelf.dto;

import nl.miwgroningen.ch10.topshelf.model.Role;
import nl.miwgroningen.ch10.topshelf.model.User;

import java.util.function.Function;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Supplies information to our frontend
 */

public record PantryUsersDTO(
        Long userId,
        String username,
        Role role
){
}

