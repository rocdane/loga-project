package app.service.authenticator;

import app.model.Profile;
import app.model.User;
import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RegistrateRequest {
    final String username;
    final String email;
    final String password;
    final User.Role role;
    final Profile profile;
}
