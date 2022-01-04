package br.com.anacarriel.controller;


import br.com.anacarriel.repository.UserRepository;
import br.com.anacarriel.security.AccountCredentialsVO;
import br.com.anacarriel.security.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    @Operation(summary = "Authenticate a user by credentials")
    @PostMapping(value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity create(@RequestBody AccountCredentialsVO data){
        try {
            var username = data.getUsername();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUserName(username);

            var token = "";

            if(user != null){
                token = tokenProvider.createToken(username, user.getRoles());
            }else {
                throw  new UserPrincipalNotFoundException("Username" + username + "not found");
            }

            Map< Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("password", password);
            return ok(model);
        }catch (Exception e){
            throw  new BadCredentialsException("Invalid username/ password supplied");
        }
    }

    }
