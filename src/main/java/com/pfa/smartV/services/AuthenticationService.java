package com.pfa.smartV.services;


import com.pfa.smartV.controllers.AuthenticationRequest;
import com.pfa.smartV.controllers.AuthenticationResponse;
import com.pfa.smartV.controllers.RegisterRequest;
import com.pfa.smartV.models.User;
import com.pfa.smartV.models.Role;
import com.pfa.smartV.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Optional<User> userByEmail = userRepository.findUserByEmail(request.getEmail());
        Optional<User> userByCIN= userRepository.findUserByCIN(request.getCIN());
        if(userByEmail.isPresent() || userByCIN.isPresent()){
            throw new RuntimeException("this user Already exist");
        }else{
            var user = User.builder()
                    .fullName(request.getFullName())
                    .email(request.getEmail())
                    .CIN(request.getCIN())
                    .tel(request.getTel())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse
                    .builder()
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .CIN(user.getCIN())
                    .tel(user.getTel())
                    .token(jwtToken)
                    .build();
        }
    }
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .CIN(user.getCIN())
                .tel(user.getTel())
                .token(jwtToken)
                .build();
    }
}
