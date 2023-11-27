package com.example.pdvapp.controller;

import com.example.pdvapp.dto.ResponseDTO;
import com.example.pdvapp.dto.UserDTO;
import com.example.pdvapp.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity createUser(@Valid @RequestBody UserDTO user){
        try{
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity updateUser(@Valid @RequestBody UserDTO userAtualizado) {
        try{
            return new ResponseEntity<>(userService.update(userAtualizado), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        try {
            userService.deleteById(id);
            return new ResponseEntity<>("Usuario deletado Com sucesso", HttpStatus.OK);
        } catch (EmptyResultDataAccessException error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
