package uz.pdp.g34springbootsecurity.dto.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresMillsIn;

    public JwtResponse(String accessToken, Long expiresMillsIn) {
        this.accessToken = accessToken;
        this.refreshToken = null;
        this.tokenType = "Bearer";
        this.expiresMillsIn = expiresMillsIn;
    }
}

