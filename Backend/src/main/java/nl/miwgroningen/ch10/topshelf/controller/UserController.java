package nl.miwgroningen.ch10.topshelf.controller;
import nl.miwgroningen.ch10.topshelf.exception.InvalidPasswordException;
import nl.miwgroningen.ch10.topshelf.model.ChangePasswordRequest;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 *
 * Talks with the frontend.
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping ("/updatepassword")
    public ResponseEntity<String> changeUserPassword (
            @RequestBody ChangePasswordRequest request) {
        User user = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (!userService.checkPassword(user, request.getPassword())) {
            throw new InvalidPasswordException("Password didn't match old password!");
        }
        userService.changeUserPassword(user, request.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
