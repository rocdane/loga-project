package app.controller;

import app.model.AuthSession;
import app.model.User;
import app.service.authenticator.AuthenticationRequest;
import app.service.authenticator.Authentication;
import app.service.authenticator.RegistrateRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/authenticate")
@AllArgsConstructor
public class AuthenticationController {
    Authentication authenticationService;

    @PostMapping("/signin")
    public AuthSession authenticate(@RequestBody AuthenticationRequest request){
        return authenticationService.authenticate(request);
    }

    @PostMapping("/registrate")
    public User register(@RequestBody RegistrateRequest request){
        return authenticationService.register(request);
    }
}
