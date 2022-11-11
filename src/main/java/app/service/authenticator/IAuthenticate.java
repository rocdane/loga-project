package app.service.authenticator;

import app.model.AuthSession;
import app.model.User;

import java.util.List;

/**
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 */
public interface IAuthenticate {
    User signUp(RegistrateRequest request);
    AuthSession signIn(AuthenticationRequest request);
    void updateUser(RegistrateRequest request, Long id);
    void deleteUser(Long id);
    List<User> allUser();
    void logout(String authSession);
}