package com.example.pdvapp.entity;


import jakarta.persistence.*;
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
    private String name;
    private boolean isEnable;
    @OneToMany(mappedBy = "user")
    private List<Sale> sales;
}
