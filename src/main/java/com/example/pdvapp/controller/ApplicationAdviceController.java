package com.example.pdvapp.controller;

import com.example.pdvapp.dto.ResponseDTO;
import com.example.pdvapp.exception.InvalidOperationException;
import com.example.pdvapp.exception.NoItemException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationAdviceController {

    @ExceptionHandler(NoItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handlerNoItemException(NoItemException exception){
        String messageError = exception.getMessage();
        return new ResponseDTO(exception.getMessage());
    }

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handlerInvalidOperationException(InvalidOperationException exception){
        String messageError = exception.getMessage();
        return new ResponseDTO(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> erros = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
                    String errorMessage = error.getDefaultMessage();
                    erros.add(errorMessage);
                }
        );

        return new ResponseDTO(erros);
    }
}
