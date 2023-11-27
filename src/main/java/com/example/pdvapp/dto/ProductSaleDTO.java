package com.example.pdvapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleDTO {
    @NotBlank(message = "Campo productId é obrigatorio!!")
    private long productId;
    @NotBlank(message = "Campo quantity é obrigatorio!!")
    @Min(1)
    private int quantity;
}
