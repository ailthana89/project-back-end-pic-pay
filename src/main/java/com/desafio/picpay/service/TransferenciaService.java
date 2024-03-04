package com.desafio.picpay.service;

import com.desafio.picpay.dto.TransferenciaDTO;
import com.desafio.picpay.entity.Transferencia;

import java.util.List;

public interface TransferenciaService {
    Transferencia realizarTransferencia(TransferenciaDTO transferenciaDTO);

    List<Transferencia> obterTransferenciasPorUsuario(Long usuarioId);
}
