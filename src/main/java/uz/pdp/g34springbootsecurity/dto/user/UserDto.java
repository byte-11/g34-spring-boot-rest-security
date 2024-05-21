package uz.pdp.g34springbootsecurity.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.g34springbootsecurity.projection.UserProjection;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements UserProjection {
    private Long id;
    private String email;
    private String username;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
