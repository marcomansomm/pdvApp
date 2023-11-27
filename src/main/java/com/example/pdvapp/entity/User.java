package com.example.pdvapp.entity;


import javax.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100,nullable = false)
    private String name;
    @Column(length = 30, nullable = false, unique = true)
    private String username;
    @Column(length = 60, nullable = false)
    private String password;
    private boolean isEnable;
    @OneToMany(mappedBy = "user")
    private List<Sale> sales;

}
