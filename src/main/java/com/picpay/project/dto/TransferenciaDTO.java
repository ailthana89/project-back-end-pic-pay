package com.picpay.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDTO {
    private Long id;
    @NotBlank
    private Long remetente;
    @NotBlank
    private Long destinatario;
    private BigDecimal valorTransferencia;
    private LocalDateTime localDateTime;

}
