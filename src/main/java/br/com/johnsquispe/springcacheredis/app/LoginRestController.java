package br.com.johnsquispe.springcacheredis.app;

import br.com.johnsquispe.springcacheredis.JwtTokenManager;
import br.com.johnsquispe.springcacheredis.UserDetail;
import br.com.johnsquispe.springcacheredis.api.LoginInput;
import br.com.johnsquispe.springcacheredis.api.LoginOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
record LoginRestController(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {

    @PostMapping("/api/login")
    public ResponseEntity<LoginOutput> authenticate(@RequestBody LoginInput loginInput) {

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginInput.getLogin(), loginInput.getPassword());

        try {

            final Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            final UserDetail userDetails = (UserDetail) authentication.getPrincipal();

            final String jwtToken = jwtTokenManager.generateToken(userDetails);

            LoginOutput loginOutput = new LoginOutput(jwtToken);

            return ResponseEntity.ok(loginOutput);

        } catch (Exception e) {
            e.printStackTrace(); //TODO - ADICIONAR O APPLICATION LOGGER
            return ResponseEntity.badRequest().build();
        }

    }

}
