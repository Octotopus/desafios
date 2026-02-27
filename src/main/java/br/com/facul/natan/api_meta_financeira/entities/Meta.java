package br.com.facul.natan.api_meta_financeira.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "meta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private UUID uuid;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private BigDecimal valorObjetivo;
    @Column(nullable = false)
    private BigDecimal valorAtual;
    @Column(nullable = false)
    private LocalDate dataCriacao;
    @Column(nullable = false)
    private Float percentual;
    @Column(nullable = false)
    private Boolean concluida;
}
