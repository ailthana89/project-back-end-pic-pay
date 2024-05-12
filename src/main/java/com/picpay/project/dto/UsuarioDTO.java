package com.picpay.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.picpay.project.entity.Documento;
import com.picpay.project.entity.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String documento;

    @JsonIgnore
    private String senha;

    @NotBlank
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    private BigDecimal ValorCarteira;

}
