package com.example.pdvapp.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private long id;
    @NotBlank(message = "Campo descrição é obrigatorio!!")
    private String description;
    @NotBlank(message = "Campo preço é obrigatorio!!")
    private BigDecimal price;
    @NotBlank(message = "Campo quantidade é obrigatorio!!")
    private int quantity;
}
