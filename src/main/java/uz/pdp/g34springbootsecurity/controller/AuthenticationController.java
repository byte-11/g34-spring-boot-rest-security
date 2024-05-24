package uz.pdp.g34springbootsecurity.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.g34springbootsecurity.dto.user.UserLoginDto;
import uz.pdp.g34springbootsecurity.dto.user.UserRegistrationDto;
import uz.pdp.g34springbootsecurity.dto.web.JwtResponse;
import uz.pdp.g34springbootsecurity.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication APIs", description = "Processes authentication based actions through apis")
public class AuthenticationController {

    private final UserServiceImpl userServiceImpl;

    public AuthenticationController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRegistrationDto dto) {
        userServiceImpl.register(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginDto dto) {
        return ResponseEntity.ok(userServiceImpl.login(dto));
    }
}
