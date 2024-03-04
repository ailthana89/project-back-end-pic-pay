package com.desafio.picpay.service.impl;

import com.desafio.picpay.dto.TransferenciaDTO;
import com.desafio.picpay.entity.Transferencia;
import com.desafio.picpay.entity.Usuario;
import com.desafio.picpay.excecoes.SaldoInsuficienteException;
import com.desafio.picpay.excecoes.UsuarioNaoEncontradoException;
import com.desafio.picpay.repository.TransferenciaRepository;
import com.desafio.picpay.repository.UsuarioRepository;
import com.desafio.picpay.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {
    private final TransferenciaRepository transferenciaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public TransferenciaServiceImpl(TransferenciaRepository transferenciaRepository, UsuarioRepository usuarioRepository) {
        this.transferenciaRepository = transferenciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Transferencia realizarTransferencia(TransferenciaDTO transferenciaDTO) {
        Usuario remetente = usuarioRepository.findById(transferenciaDTO.getRemetente())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário remetente não encontrado com ID: " + transferenciaDTO.getRemetente()));

        Usuario destinatario = usuarioRepository.findById(transferenciaDTO.getDestinatario())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário destinatário não encontrado com ID: " + transferenciaDTO.getDestinatario()));

        BigDecimal valorTransferencia = transferenciaDTO.getValor();

        if (remetente.getCarteira() == null || remetente.getCarteira().compareTo(valorTransferencia) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar a transferência");
        }

        remetente.debitarCarteira(valorTransferencia);

        destinatario.creditarCarteira(valorTransferencia);

        Transferencia transferencia = new Transferencia(remetente, destinatario, valorTransferencia);
        transferenciaRepository.save(transferencia);

        return transferencia;
    }

    @Override
    public List<Transferencia> obterTransferenciasPorUsuario(Long usuarioId) {

        return transferenciaRepository.findByRemetenteIdOrDestinatarioId(usuarioId, usuarioId);
    }

}

