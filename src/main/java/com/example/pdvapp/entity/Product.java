package com.example.pdvapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "description", nullable = false)
    @NotBlank(message = "O campo descrição é obrigatorio")
    private String description;
    @Column(nullable = false, name = "price", length = 20, precision = 2)
    @NotNull(message = "O campo preço é obrigatorio")
    private BigDecimal price;
    @Column(nullable = false, name = "quantity")
    @NotNull(message = "O campo quantidade é obrigatorio")
    @Min(1)
    private long quantity;
}
