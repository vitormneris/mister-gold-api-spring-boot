package com.mistergold.mistergold.adapters.web.auth;

import com.mistergold.mistergold.adapters.web.auth.dto.AuthorizationDTO;
import com.mistergold.mistergold.application.domain.abstracts.UserAbstract;
import com.mistergold.mistergold.configuration.web.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autorizacao")
@RequiredArgsConstructor
public class AuthorizationResource {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String authenticate(@RequestBody AuthorizationDTO data) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(data.email(), data.password());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var user = (UserAbstract) authenticate.getPrincipal();
        return tokenService.tokenGeneration(user);
    }
}
