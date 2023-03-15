package nl.miwgroningen.ch10.topshelf.security.auth;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.exception.UsernameTakenException;
import nl.miwgroningen.ch10.topshelf.security.config.JwtService;
import nl.miwgroningen.ch10.topshelf.model.Role;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Jacob Visser
 * Controls the User repository
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws UsernameTakenException {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        List<User> existingUsers = userRepository.findAll()
                .stream()
                .filter(x -> request.getUsername().equals(x.getUsername()))
                .toList();

        if (existingUsers.isEmpty()) {
            userRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new UsernameTakenException("Username already taken");
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).
                orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .build();
    }
}
