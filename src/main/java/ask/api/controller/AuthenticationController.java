package ask.api.controller;

import ask.api.domain.user.Users;
import ask.api.domain.user.UsersRepository;
import ask.api.domain.user.dto.UserLogin;
import ask.api.infra.security.TokenDataJWT;
import ask.api.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthenticationController {

    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity login(@RequestBody @Valid UserLogin user) {
        var autenticationToken = new UsernamePasswordAuthenticationToken(user.email(), user.password());
        var autentication = manager.authenticate(autenticationToken);

        var tokenJWT = tokenService.generateToken((Users) autentication.getPrincipal());

        return ResponseEntity.ok(new TokenDataJWT(tokenJWT));
    }

}
