package com.desafio.picpay.service;

import java.math.BigDecimal;

public interface AutorizacaoService {
    boolean autorizarTransferencia(BigDecimal valor);
}
