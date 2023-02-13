package nl.miwgroningen.ch10.topshelf.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    ) throws IOException {
        if(captchaService.verify(request.getCaptchaResponse())) {
            return ResponseEntity.ok(service.register(request));
        } else {
            throw new RuntimeException("Invalid reCaptcha");
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
