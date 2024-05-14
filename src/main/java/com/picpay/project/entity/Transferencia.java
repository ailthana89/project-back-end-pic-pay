package com.picpay.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transferencias")
@EqualsAndHashCode(of = "id")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
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

    private BigDecimal valorTransferencia;

    private LocalDateTime localDateTime = LocalDateTime.now();

    public Transferencia(Usuario remetente, Usuario destinatario, BigDecimal valorTransferencia

    ) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valorTransferencia = valorTransferencia;
    }
}
