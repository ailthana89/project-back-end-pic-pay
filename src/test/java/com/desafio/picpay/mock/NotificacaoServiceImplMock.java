package com.desafio.picpay.mock;

import com.desafio.picpay.dto.NotificacaoPayload;
import com.desafio.picpay.entity.Usuario;
import com.desafio.picpay.service.NotificacaoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class NotificacaoServiceImplMock implements NotificacaoService {

    private final RestTemplate restTemplate;

    public NotificacaoServiceImplMock(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String enviarNotificacaoPagamento(Usuario remetente, Usuario destinatario, BigDecimal valor) {
        String notificacaoUrl = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";

        NotificacaoPayload payload = new NotificacaoPayload(valor, remetente.getId(), destinatario.getId());

        restTemplate.postForEntity(notificacaoUrl, payload, String.class);
        return notificacaoUrl;
    }
}
