package uz.pdp.g34springbootsecurity.service.impl;

import java.util.List;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.g34springbootsecurity.domain.UserEntity;
import uz.pdp.g34springbootsecurity.dto.user.UserLoginDto;
import uz.pdp.g34springbootsecurity.dto.user.UserRegistrationDto;
import uz.pdp.g34springbootsecurity.dto.web.JwtResponse;
import uz.pdp.g34springbootsecurity.exception.UserAlreadyExistsException;
import uz.pdp.g34springbootsecurity.exception.UserNotFoundException;
import uz.pdp.g34springbootsecurity.projection.UserProjection;
import uz.pdp.g34springbootsecurity.provider.JwtProvider;
import uz.pdp.g34springbootsecurity.repository.UserRepository;
import uz.pdp.g34springbootsecurity.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void register(UserRegistrationDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new UserAlreadyExistsException("User already exists by email: " + dto.email());
        }
        userRepository.save(
                UserEntity.builder()
                        .email(dto.email())
                        .username(dto.username())
                        .password(passwordEncoder.encode(dto.password()))
                        .build()
        );
    }

    @Override
    public JwtResponse login(UserLoginDto dto) {
        final UserEntity user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(
                       () -> new UserNotFoundException("User not found by email: " + dto.getEmail())
                );
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Bad credentials");
        }
        return new JwtResponse(jwtProvider.generateToken(user), jwtProvider.getExpiration());
    }

    @Override
    public List<UserProjection> getUsers() {
        return userRepository.findAllUsers();
    }
}
