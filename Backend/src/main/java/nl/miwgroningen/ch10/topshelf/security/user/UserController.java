package nl.miwgroningen.ch10.topshelf.security.user;

import nl.miwgroningen.ch10.topshelf.exception.InvalidPasswordException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/updatePassword")
    public String changeUserPassword (
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("password") String password) {
        User user = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (!userService.checkPassword(user, oldPassword)) {
            throw new InvalidPasswordException("Onjuist wachtwoord!");
        }
        userService.changeUserPassword(user, password);
        return "Wachtwoord succesvol veranderd!";
    }
}
