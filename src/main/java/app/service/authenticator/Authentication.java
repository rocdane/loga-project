package app.service.authenticator;

import app.model.AuthSession;
import app.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vendor.io.Validation;

/**
 * Authentication service
 * @author rochdane sabi
 * @email rocdanesabi@gmail.com
 * @version 2.1.1
 * @since 2.0.1
 */
@Service
@AllArgsConstructor
public class Authentication {
    private final Authenticate authenticate;

    /**
     * authenticate method
     * @see AuthenticationRequest
     * @param request
     * @return session's token
     */
    public AuthSession authenticate(AuthenticationRequest request){

        boolean isValid = Validation.is_valid_email(request.getEmail());

        if(!isValid){
            throw new IllegalStateException("Utilisateur non valide");
        }

        return authenticate.signIn(request);
    }

    /**
     * register method
     * @see RegistrateRequest
     * @param request
     * @return created User
     */
    public User register(RegistrateRequest request){
        return authenticate.signUp(request);
    }
}
