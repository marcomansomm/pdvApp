package com.example.pdvapp.controller;

import com.example.pdvapp.dto.UserDTO;
import com.example.pdvapp.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private UserService userService;

    public SignUpController(@Autowired UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity createUser(@Valid @RequestBody UserDTO user){
        try{
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
