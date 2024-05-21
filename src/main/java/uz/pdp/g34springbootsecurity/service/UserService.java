package uz.pdp.g34springbootsecurity.service;

import java.util.List;
import uz.pdp.g34springbootsecurity.dto.user.UserDto;
import uz.pdp.g34springbootsecurity.dto.user.UserLoginDto;
import uz.pdp.g34springbootsecurity.dto.user.UserRegistrationDto;
import uz.pdp.g34springbootsecurity.dto.web.JwtResponse;
import uz.pdp.g34springbootsecurity.projection.UserProjection;

public interface UserService {
    void register(UserRegistrationDto data);

    JwtResponse login(UserLoginDto data);

    List<UserProjection> getUsers();
}
