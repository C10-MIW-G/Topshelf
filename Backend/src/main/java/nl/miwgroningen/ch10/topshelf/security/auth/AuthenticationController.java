package nl.miwgroningen.ch10.topshelf.security.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.exception.InvalidReCaptchaException;
import nl.miwgroningen.ch10.topshelf.exception.UserNotFoundException;
import nl.miwgroningen.ch10.topshelf.model.ResetPasswordRequest;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Jacob Visser
 * Talks with the frontend
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/topshelf")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final CaptchaService captchaService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws InvalidReCaptchaException {
        if(captchaService.verify(request.getCaptchaResponse())) {
            return ResponseEntity.ok(service.register(request));
        } else {
            throw new InvalidReCaptchaException("Invalid reCaptcha");
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<String> resetUserPassword (
            @RequestBody ResetPasswordRequest request) throws MessagingException, InvalidReCaptchaException {
        User user = userService.findUserByEmail(request.getEmail());

        if(captchaService.verify(request.getCaptchaResponse()) &&
                userService.checkUserEmail(user, request.getEmail())) {
            userService.resetPassword(user);
            userService.sendResetPasswordMailToUser(user);

            return new ResponseEntity<>(HttpStatus.OK);
        } else if (!captchaService.verify(request.getCaptchaResponse())) {
            throw new InvalidReCaptchaException("Invalid reCaptcha");
        } else if (!userService.checkUserEmail(user, request.getEmail())) {
            throw new UserNotFoundException("User with email: " + request.getEmail() + " was not found");
        } else {
            return new ResponseEntity<>("Reset password failed. Please try again.", HttpStatus.OK);
        }
    }
}
