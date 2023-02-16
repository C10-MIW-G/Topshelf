package nl.miwgroningen.ch10.topshelf.security.auth;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.exception.InvalidReCaptchaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Jacob Visser
 * <p>
 * Dit is wat het programma doet.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/topshelf")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final CaptchaService captchaService;

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
}
