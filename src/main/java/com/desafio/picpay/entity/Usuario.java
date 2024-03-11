package com.desafio.picpay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuario")
@Table(name = "usuario")
@EqualsAndHashCode(of = "id")
public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String nomeCompleto;

        @NotBlank
        @Column(unique = true)
        private String documento;

        @NotBlank
        @Email
        @Column(unique = true)
        private String email;

        @NotBlank
        private String senha;

        @PositiveOrZero
        private BigDecimal carteira;

        @Enumerated(EnumType.STRING)
        private TipoUsuario tipoUsuario;

//        public void debitarCarteira(BigDecimal valorTransferencia) {
//                if (valorTransferencia == null || valorTransferencia.compareTo(BigDecimal.ZERO) <= 0) {
//                        throw new IllegalArgumentException("O valor da transferência deve ser positivo.");
//                }
//
//                if (carteira == null || carteira.compareTo(valorTransferencia) < 0) {
//                        throw new SaldoInsuficienteException("Saldo insuficiente para realizar a transferência.");
//                }
//
//                carteira = carteira.subtract(valorTransferencia);
//        }
//
//        public void creditarCarteira(BigDecimal valorTransferencia) {
//                if (valorTransferencia == null || valorTransferencia.compareTo(BigDecimal.ZERO) <= 0) {
//                        throw new IllegalArgumentException("O valor a ser creditado deve ser positivo.");
//                }
//
//                carteira = carteira == null ? valorTransferencia : carteira.add(valorTransferencia);
//        }
}
