package com.example.pdvapp.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;
    @NotBlank(message = "Campo nome é obrigatorio!!")
    private String name;
    @NotBlank(message = "Campo username é obrigatorio!!")
    private String username;
    @NotBlank(message = "Campo password é obrigatorio!!")
    private String password;
    private boolean enable;

}
