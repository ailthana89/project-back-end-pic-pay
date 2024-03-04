package com.desafio.picpay.service.impl;

import com.desafio.picpay.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
class NotificacaoServiceImplTest {

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private NotificacaoServiceImpl notificacaoService;

    @Test
    void enviarNotificacaoPagamento_ComRemetenteNulo_DeveLancarExcecao() {
        Usuario destinatario = new Usuario();
        BigDecimal valor = BigDecimal.TEN;

        assertThrows(NullPointerException.class,
                () -> notificacaoService.enviarNotificacaoPagamento(null, destinatario, valor));
    }

    @Test
    void enviarNotificacaoPagamento_ComDestinatarioNulo_DeveLancarExcecao() {
        Usuario remetente = new Usuario();
        BigDecimal valor = BigDecimal.TEN;

        assertThrows(NullPointerException.class,
                () -> notificacaoService.enviarNotificacaoPagamento(remetente, null, valor));
    }

    @Test
    void enviarNotificacaoPagamento_ComValorNulo_DeveLancarExcecao() {
        Usuario remetente = new Usuario();
        Usuario destinatario = new Usuario();

        assertThrows(NullPointerException.class,
                () -> notificacaoService.enviarNotificacaoPagamento(remetente, destinatario, null));
    }

}







