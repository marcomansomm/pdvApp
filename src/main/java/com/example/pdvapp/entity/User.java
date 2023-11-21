package com.example.pdvapp.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100,nullable = false)
    @NotBlank(message = "Campo nome é obrigatorio!!")
    private String name;
    @Column(length = 30, nullable = false)
    @NotBlank(message = "Campo Username é obrigatorio!!")
    private String username;
    @NotBlank(message = "Campo senha é obrigatorio!!")
    private String password;
    private boolean isEnable;
    @OneToMany(mappedBy = "user")
    private List<Sale> sales;
}
