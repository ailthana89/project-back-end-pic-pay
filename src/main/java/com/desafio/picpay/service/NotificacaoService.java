package com.desafio.picpay.service;

import com.desafio.picpay.entity.Usuario;

import java.math.BigDecimal;

public interface NotificacaoService {
    String enviarNotificacaoPagamento(Usuario remetente, Usuario destinatario, BigDecimal valor);
}
