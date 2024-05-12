package com.picpay.project.excecoes;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioNotFoundException extends UsernameNotFoundException {

    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
