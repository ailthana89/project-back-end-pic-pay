package com.desafio.picpay.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {

    @NotNull
    private Long remetente;

    @NotNull
    private Long destinatario;

    @Positive
    private BigDecimal valor;


}
