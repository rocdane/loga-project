package app.service.authenticator;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthenticationRequest {
    protected final String username;
    protected final String email;
    protected final String password;
}
