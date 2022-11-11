package app.service.authenticator;

import app.model.AuthSession;
import app.model.User;
import app.dao.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author rochdane sabi
 * @email rocdanesabi@gmail.com
 * @version 2.1.1
 */
@Service
@AllArgsConstructor
public class Authenticate implements UserDetailsService, IAuthenticate {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * TODO: cette méthode permet de créer un objet Session pour un utilisateur.
     *
     * @param request
     * @return String
     */
    @Override
    public AuthSession signIn(AuthenticationRequest request) {

        AuthSession authSession = null;

        User user = userRepository.findByUsernameAndPassword(request.getUsername(),
                bCryptPasswordEncoder.encode(request.getPassword())).get();

        if ( user.isActive()) {

            authSession = new AuthSession();
            authSession.setUser(user);
            authSession.setDateCreate(new Date());
        }

        return authSession;
    }

    /**
     * TODO:Cette méthode permet de détruire un objet Session d'un utilisateur.
     * @param token
     */
    @Override
    public void logout(String token) {
        //AuthSession = sessionRepository.findByToken(token);
        //authSession.setDateClose(new Date());
        //sessionRepository.save(authSession);
    }

    @Override
    public User signUp(RegistrateRequest request) {

        boolean userExist = userRepository.findByUsername(request.getUsername())
                .isPresent();

        if(userExist){
            throw new IllegalStateException("Utilisateur existant");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        User user = new User(request.getUsername(),request.getEmail(),encodedPassword);
        user.setRole(request.getRole());
        user.setProfile(request.getProfile());

        return userRepository.save(user);
    }

    /**
     * TODO: Cette méthode permet de mettre à jour un objet User.
     * @param request, id
     */
    @Override
    public void updateUser(RegistrateRequest request, Long id){
        User user = userRepository.findById(id).get();
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> allUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Aucun compte associé à l'utilisateur %s",username)));
    }
}
