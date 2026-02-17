package it.giovanna.taskmanager.service;

import it.giovanna.taskmanager.dto.auth.*;
import it.giovanna.taskmanager.model.User;
import it.giovanna.taskmanager.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterRequest req) {
        if(userService.usernameExists(req.username())){
            throw new RuntimeException("Username already taken");
        }
        User u= User.builder().username(req.username())
                .passwordHash(passwordEncoder.encode(req.password()))
                .build();

        userService.create(u);
    }

    public AuthResponse login(LoginRequest req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(),req.password()));

        String token=  jwtService.generateToken(req.username());
        return  new AuthResponse(token);
    }
}
