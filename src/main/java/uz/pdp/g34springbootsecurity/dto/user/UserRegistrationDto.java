package uz.pdp.g34springbootsecurity.dto.user;

public record UserRegistrationDto(
        String username,
        String email,
        String password
) {
}
