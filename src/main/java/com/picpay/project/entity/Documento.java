package com.picpay.project.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

@ToString
@Component
@Embeddable
public class Documento {

    @CPF
    private String cpf;

    @CNPJ
    private String cnpj;

    public Documento(String cpf, String cnpj) {
        this.cpf = cpf;
        this.cnpj = cnpj;
    }
    public Documento(){}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
