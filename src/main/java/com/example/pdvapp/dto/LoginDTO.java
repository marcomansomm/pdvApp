package com.example.pdvapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "O campo Username não pode ser vazio")
    private String username;
    @NotBlank(message = "O campo Password não pode ser vazio")
    private String password;
}
