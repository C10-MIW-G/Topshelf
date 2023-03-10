package nl.miwgroningen.ch10.topshelf.controller;

import jakarta.mail.MessagingException;
import nl.miwgroningen.ch10.topshelf.dto.PantryUsersDTO;
import nl.miwgroningen.ch10.topshelf.exception.InvalidPasswordException;
import nl.miwgroningen.ch10.topshelf.model.ChangePasswordRequest;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/updatepassword")
    public ResponseEntity<String> changeUserPassword(
            @RequestBody ChangePasswordRequest request) {
        User user = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (!userService.checkPassword(user, request.getPassword())) {
            throw new InvalidPasswordException("Password didn't match old password!");
        }
        userService.changeUserPassword(user, request.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/inviteuser/{pantryId}")
    public ResponseEntity<String> inviteUserToPantry(
            @RequestBody PantryUsersDTO emailUser, @PathVariable("pantryId") Pantry pantryId) throws MessagingException {
        System.out.println("User "+ emailUser);

        boolean existingUser = userService.checkUserEmail(emailUser.email());

        if (existingUser) {
            User user = userService.findUserByEmail(emailUser.email());
            userService.inviteUserToPantry(user, pantryId);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/checkuser/{pantryId}")
    public ResponseEntity<PantryUsersDTO> checkIfCurrentUserIsPantryAdmin(@PathVariable("pantryId") Long pantryId) {
        System.out.println("pantryID: " + pantryId);
        System.out.println();
        User user = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("User: " + user);
        System.out.println();
        List<PantryUsersDTO> currentAdmin = userService.checkIfUserIsPantryAdmin(user, pantryId);

        System.out.println("List of pantry admins: " + currentAdmin);
        System.out.println();

        if (currentAdmin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            PantryUsersDTO pantryUsersDTO = userService.findPantryUsersDTOByUsername(user.getUsername());
            System.out.println(pantryUsersDTO);
            return new ResponseEntity<>(pantryUsersDTO, HttpStatus.OK);
        }
    }
}
