package com.desafio.picpay.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transferencia")
@Table(name = "transferencia")
@EqualsAndHashCode(of = "id")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remetente_id", nullable = false)
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Usuario destinatario;

    private BigDecimal valor;

    private LocalDateTime localDateTime;

//    public Transferencia(Usuario remetente, Usuario destinatario, BigDecimal valor) {
//        this.remetente = remetente;
//        this.destinatario = destinatario;
//        this.valor = valor;
//    }
}
