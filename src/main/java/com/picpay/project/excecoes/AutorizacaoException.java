package com.picpay.project.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AutorizacaoException extends RuntimeException {
    public AutorizacaoException(String message) {
        super(message);
    }
}

