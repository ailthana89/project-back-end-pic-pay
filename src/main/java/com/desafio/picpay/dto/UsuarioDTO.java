package com.desafio.picpay.dto;

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
public class UsuarioDTO {

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @CNPJ
    private String cnpj;


    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @PositiveOrZero
    private BigDecimal carteira;

    public UsuarioDTO(String nomeCompleto, String cpf, String cnpj, String email, String senha, BigDecimal carteira) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.email = email;
        this.senha = senha;
        this.carteira = carteira;
    }

    public UsuarioDTO() {

    }
}
