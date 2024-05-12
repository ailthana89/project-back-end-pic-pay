package com.picpay.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    @Column(unique = true)
    private String documento;

    private String senha;

    @Email
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    private BigDecimal ValorCarteira;

}
