package uz.pdp.g34springbootsecurity.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "UserLogin", title = "User Login Data", description = "Data that is used to login for authentication")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @Schema(name = "email", description = "Registered email for authentication")
    private String email;
    @Schema(name = "password",
            description = "Authentication password", pattern = """
            The password must have at least one lowercase, one uppercase character
            one digit and one symbol
            """)
    private String password;
}
