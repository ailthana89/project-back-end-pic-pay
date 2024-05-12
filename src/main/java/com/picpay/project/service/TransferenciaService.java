package com.picpay.project.service;

import com.picpay.project.dto.TransferenciaDTO;
import com.picpay.project.entity.Transferencia;
import com.picpay.project.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TransferenciaService {


    @Transactional
    Transferencia realizarTransferencia(TransferenciaDTO transferenciaDTO);

    Optional<Transferencia> obterTransferenciaPorId(Long usuarioId);
}
