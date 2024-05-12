package com.picpay.project.service;

import com.picpay.project.entity.Usuario;

public interface NotificacaoService {
    void enviarNotificacaoTransferencia(Usuario beneficiario);
}
