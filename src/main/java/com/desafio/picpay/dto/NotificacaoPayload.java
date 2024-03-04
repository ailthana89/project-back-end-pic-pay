package com.desafio.picpay.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoPayload {

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Long pagador;

    @NotNull
    private Long beneficiario;
}
