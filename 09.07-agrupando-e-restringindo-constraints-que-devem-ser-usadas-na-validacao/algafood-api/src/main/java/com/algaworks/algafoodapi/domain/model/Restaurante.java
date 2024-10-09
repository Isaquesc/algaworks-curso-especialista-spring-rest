package com.algaworks.algafoodapi.domain.model;

import com.algaworks.algafoodapi.infrastucture.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(groups = Groups.CadastroRestaurante.class)
    private String nome;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime data_cadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime data_atualizacao;

//    @DecimalMin("0")
    @PositiveOrZero (groups = Groups.CadastroRestaurante.class)
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @Valid
    @NotNull (groups = Groups.CadastroRestaurante.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;

    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formaPagamentos = new ArrayList<>();
}
