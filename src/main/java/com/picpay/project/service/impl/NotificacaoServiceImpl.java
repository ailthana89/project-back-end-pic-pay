package com.picpay.project.service.impl;

import com.picpay.project.entity.Usuario;
import com.picpay.project.service.NotificacaoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void enviarNotificacaoTransferencia(Usuario beneficiario) {
        String notificadorUrl = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";
        restTemplate.postForEntity(notificadorUrl, beneficiario, Void.class);
    }
}