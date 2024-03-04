package com.desafio.picpay.service.impl;

import com.desafio.picpay.mock.AutorizacaoServiceImplMock;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AutorizacaoServiceImplTest {

    @Test
    void autorizarTransferencia_DeveRetornarTrueQuandoAutorizado() {
        RestTemplate restTemplateMock = mock(RestTemplate.class);
        String autorizadorUrl = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

        when(restTemplateMock.getForEntity(autorizadorUrl, String.class))
                .thenReturn(new ResponseEntity<>("Autorizado", HttpStatus.OK));

        AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock(restTemplateMock);

        boolean resultado = autorizacaoService.autorizarTransferencia(BigDecimal.TEN);

        assertTrue(resultado);
    }

    @Test
    void autorizarTransferencia_DeveRetornarFalseQuandoNaoAutorizado() {
        RestTemplate restTemplateMock = mock(RestTemplate.class);
        String autorizadorUrl = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

        when(restTemplateMock.getForEntity(autorizadorUrl, String.class))
                .thenReturn(new ResponseEntity<>("Nao Autorizado", HttpStatus.OK));

        AutorizacaoServiceImplMock autorizacaoService = new AutorizacaoServiceImplMock(restTemplateMock);

        boolean resultado = autorizacaoService.autorizarTransferencia(BigDecimal.TEN);

        assertFalse(resultado);
    }
}

