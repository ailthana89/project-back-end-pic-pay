package com.picpay.project.service.impl;

import com.picpay.project.dto.TransferenciaDTO;
import com.picpay.project.entity.TipoUsuario;
import com.picpay.project.entity.Transferencia;
import com.picpay.project.entity.Usuario;
import com.picpay.project.excecoes.SaldoInsuficienteException;
import com.picpay.project.excecoes.TransferenciaRuntimeException;
import com.picpay.project.excecoes.UsuarioNotFoundException;
import com.picpay.project.repository.TransferenciaRepository;
import com.picpay.project.repository.UsuarioRepository;
import com.picpay.project.service.AutorizacaoService;
import com.picpay.project.service.NotificacaoService;
import com.picpay.project.service.TransferenciaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {
    private final UsuarioRepository usuarioRepository;

    private final TransferenciaRepository transferenciaRepository;
    private final AutorizacaoService autorizacaoService;
    private final NotificacaoService notificacaoService;

    public TransferenciaServiceImpl(UsuarioRepository usuarioRepository, TransferenciaRepository transferenciaRepository, AutorizacaoService autorizacaoService, NotificacaoService notificacaoService) {
        this.usuarioRepository = usuarioRepository;
        this.transferenciaRepository = transferenciaRepository;
        this.autorizacaoService = autorizacaoService;
        this.notificacaoService = notificacaoService;
    }

    @Override
    @Transactional
    public Transferencia realizarTransferencia(TransferenciaDTO transferenciaDTO) {
         Usuario remetente = usuarioRepository.findById(transferenciaDTO.getRemetente())
                .orElseThrow(() -> new UsuarioNotFoundException("Remetente não encontrado"));

        Usuario beneficiario = usuarioRepository.findById(transferenciaDTO.getDestinatario())
                .orElseThrow(() -> new UsuarioNotFoundException("Beneficiário não encontrado"));

        if (remetente.getTipoUsuario() == TipoUsuario.LOJISTA) {
            throw new TransferenciaRuntimeException("Lojistas não podem realizar transferências");
        }

        if (remetente.getValorCarteira().compareTo(transferenciaDTO.getValorTransferencia()) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar a transferência");
        }

        //MOCK NAO DISPONIVEL
        //if (autorizacaoService.autorizarTransferencia()) {
        //throw new AutorizacaoException("Autorização para a transferência não concedida");
//        }

        Transferencia transferencia = new Transferencia(remetente, beneficiario, transferenciaDTO.getValorTransferencia());

        BigDecimal novoSaldoPagador = remetente.getValorCarteira().subtract(transferenciaDTO.getValorTransferencia());
        remetente.setValorCarteira(novoSaldoPagador);

        BigDecimal novoSaldoBeneficiario = beneficiario.getValorCarteira().add(transferenciaDTO.getValorTransferencia());
        beneficiario.setValorCarteira(novoSaldoBeneficiario);

        usuarioRepository.save(remetente);
        usuarioRepository.save(beneficiario);

        //MOCK NAO DISPONIVEL
        //notificacaoService.enviarNotificacaoTransferencia(beneficiario);

        return transferencia;
    }

    @Override
    public Optional<Transferencia> obterTransferenciaPorId(Long usuarioId) {

        return transferenciaRepository.findById(usuarioId);
    }
}