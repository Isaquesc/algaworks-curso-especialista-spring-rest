package com.algaworks.algafoodapi.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Restaurante {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;
}
