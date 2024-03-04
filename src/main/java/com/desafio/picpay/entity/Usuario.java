package com.desafio.picpay.entity;

import com.desafio.picpay.excecoes.SaldoInsuficienteException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String nomeCompleto;

        @NotBlank
        @CPF
        @Column(unique = true)
        private String cpf;

        @NotBlank
        @CNPJ
        @Column(unique = true)
        private String cnpj;

        @NotBlank
        @Email
        @Column(unique = true)
        private String email;

        @NotBlank
        private String senha;

        @PositiveOrZero
        private BigDecimal carteira;

        public void debitarCarteira(BigDecimal valorTransferencia) {
                if (valorTransferencia == null || valorTransferencia.compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException("O valor da transferência deve ser positivo.");
                }

                if (carteira == null || carteira.compareTo(valorTransferencia) < 0) {
                        throw new SaldoInsuficienteException("Saldo insuficiente para realizar a transferência.");
                }

                carteira = carteira.subtract(valorTransferencia);
        }

        public void creditarCarteira(BigDecimal valorTransferencia) {
                if (valorTransferencia == null || valorTransferencia.compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException("O valor a ser creditado deve ser positivo.");
                }

                carteira = carteira == null ? valorTransferencia : carteira.add(valorTransferencia);
        }
}
