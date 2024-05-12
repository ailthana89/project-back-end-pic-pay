package com.picpay.project.service;

import com.picpay.project.entity.Usuario;

import java.math.BigDecimal;

public interface CarteiraService {

    Usuario debitarCarteira(Usuario remetente, BigDecimal valorCarteira);

    Usuario creditarCarteira(Usuario destinatario, BigDecimal valorCarteira);
}
