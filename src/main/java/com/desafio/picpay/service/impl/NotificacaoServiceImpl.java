package com.desafio.picpay.service.impl;


import com.desafio.picpay.dto.NotificacaoPayload;
import com.desafio.picpay.entity.Usuario;
import com.desafio.picpay.excecoes.UsuarioNullPointerException;
import com.desafio.picpay.service.NotificacaoService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String enviarNotificacaoPagamento(Usuario remetente, Usuario destinatario, BigDecimal valor) {
        if (remetente == null || destinatario == null || valor == null) {
            throw new UsuarioNullPointerException("Os parâmetros não podem ser nulos.");
        }

        String notificacaoUrl = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";

        NotificacaoPayload payload = new NotificacaoPayload(valor, remetente.getId(), destinatario.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NotificacaoPayload> request = new HttpEntity<>(payload, headers);

        restTemplate.postForEntity(notificacaoUrl, request, String.class);

        return notificacaoUrl;
    }
}
