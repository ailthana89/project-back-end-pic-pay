package com.desafio.picpay.service.impl;

import com.desafio.picpay.service.AutorizacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean autorizarTransferencia(BigDecimal valor) {
        String autorizadorUrl = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

        ResponseEntity<String> response = restTemplate.getForEntity(autorizadorUrl, String.class);

        return "Autorizado".equalsIgnoreCase(response.getBody());
    }
}
