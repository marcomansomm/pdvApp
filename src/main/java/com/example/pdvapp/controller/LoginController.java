package com.example.pdvapp.controller;

import com.example.pdvapp.dto.ResponseDTO;
import com.example.pdvapp.dto.LoginDTO;
import javax.validation.Valid;

import com.example.pdvapp.dto.TokenDTO;
import com.example.pdvapp.security.CustomUserDetailService;
import com.example.pdvapp.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private JwtService jwtService;

    @Value("${security.jwt.expiration}")
    private String expiration;

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody LoginDTO loginData){
        try{

            String token = jwtService.generateToken(loginData.getUsername());
            userDetailService.verifyUserCredentials(loginData);
            //gerar token
            return new ResponseEntity<>(new TokenDTO(token, expiration), HttpStatus.OK);
        } catch(Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
