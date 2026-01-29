package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "regional")
@Data
public class Regional {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nome;
    
    private Boolean ativo;
}