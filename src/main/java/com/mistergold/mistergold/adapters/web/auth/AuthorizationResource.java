package com.mistergold.mistergold.adapters.web.auth;

import com.mistergold.mistergold.adapters.web.auth.dto.AuthorizationDTO;
import com.mistergold.mistergold.adapters.web.auth.dto.UserIdDTO;
import com.mistergold.mistergold.adapters.web.auth.dto.TokenDTO;
import com.mistergold.mistergold.application.domain.abstracts.UserAbstract;
import com.mistergold.mistergold.configuration.web.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autorizacao")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9091")
public class AuthorizationResource {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> authenticate(@RequestBody AuthorizationDTO data) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(data.email(), data.password());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var user = (UserAbstract) authenticate.getPrincipal();
        return ResponseEntity.ok().body(new TokenDTO(tokenService.tokenGeneration(user)));
    }

    @GetMapping(value = "/token/cliente")
    public ResponseEntity<UserIdDTO> validation(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok().body(new UserIdDTO(tokenService.getClaimId(token)));
    }

    @GetMapping(value = "/token/administrador")
    public ResponseEntity<UserIdDTO> validationAdm(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok().body(new UserIdDTO(tokenService.getClaimId(token)));
    }
}
