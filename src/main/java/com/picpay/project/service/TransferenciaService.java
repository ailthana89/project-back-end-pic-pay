package com.picpay.project.service;

import com.picpay.project.dto.TransferenciaDTO;
import com.picpay.project.entity.Transferencia;
import com.picpay.project.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface TransferenciaService {


    Usuario obterUsuario(Long usuarioId);

    void validarRemetente(Usuario remetente);

    void verificarSaldo(Usuario remetente, BigDecimal valorTransferencia);

    void atualizarSaldo(Usuario remetente, Usuario beneficiario, BigDecimal valorTransferencia);

    @Transactional
    Transferencia realizarTransferencia(TransferenciaDTO transferenciaDTO);

    Optional<Transferencia> obterTransferenciaPorId(Long usuarioId);
}
