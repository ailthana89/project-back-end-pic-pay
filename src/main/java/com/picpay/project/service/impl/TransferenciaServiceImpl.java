package com.picpay.project.service.impl;

import com.picpay.project.dto.TransferenciaDTO;
import com.picpay.project.entity.TipoUsuario;
import com.picpay.project.entity.Transferencia;
import com.picpay.project.entity.Usuario;
import com.picpay.project.excecoes.SaldoInsuficienteException;
import com.picpay.project.excecoes.TransferenciaRuntimeException;
import com.picpay.project.excecoes.UsuarioNotFoundException;
import com.picpay.project.mapper.TransferenciaMapper;
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

    private final TransferenciaMapper transferenciaMapper;

    private final TransferenciaRepository transferenciaRepository;
    private final AutorizacaoService autorizacaoService;
    private final NotificacaoService notificacaoService;

    public TransferenciaServiceImpl(UsuarioRepository usuarioRepository, TransferenciaMapper transferenciaMapper, TransferenciaRepository transferenciaRepository, AutorizacaoService autorizacaoService, NotificacaoService notificacaoService) {
        this.usuarioRepository = usuarioRepository;
        this.transferenciaMapper = transferenciaMapper;
        this.transferenciaRepository = transferenciaRepository;
        this.autorizacaoService = autorizacaoService;
        this.notificacaoService = notificacaoService;
    }

    @Override
    public Usuario obterUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));
    }

    @Override
    public void validarRemetente(Usuario remetente) {
        if (remetente.getTipoUsuario() == TipoUsuario.LOJISTA) {
            throw new TransferenciaRuntimeException("Lojistas não podem realizar transferências");
        }
    }

    @Override
    public void verificarSaldo(Usuario remetente, BigDecimal valorTransferencia) {
        if (remetente.getValorCarteira().compareTo(valorTransferencia) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar a transferência");
        }
    }

    private Transferencia criarTransferencia(Usuario remetente, Usuario beneficiario, BigDecimal valorTransferencia) {
        return new Transferencia(remetente, beneficiario, valorTransferencia);
    }

    @Override
    public void atualizarSaldo(Usuario remetente, Usuario beneficiario, BigDecimal valorTransferencia) {
        BigDecimal novoSaldoPagador = remetente.getValorCarteira().subtract(valorTransferencia);
        remetente.setValorCarteira(novoSaldoPagador);

        BigDecimal novoSaldoBeneficiario = beneficiario.getValorCarteira().add(valorTransferencia);
        beneficiario.setValorCarteira(novoSaldoBeneficiario);

        usuarioRepository.save(remetente);
        usuarioRepository.save(beneficiario);
    }

    @Override
    @Transactional
    public Transferencia realizarTransferencia(TransferenciaDTO transferenciaDTO) {
        Usuario remetente = obterUsuario(transferenciaDTO.getRemetente());
        Usuario beneficiario = obterUsuario(transferenciaDTO.getDestinatario());

        validarRemetente(remetente);
        verificarSaldo(remetente, transferenciaDTO.getValorTransferencia());

        Transferencia transferencia = criarTransferencia(remetente, beneficiario, transferenciaDTO.getValorTransferencia());

        atualizarSaldo(remetente, beneficiario, transferenciaDTO.getValorTransferencia());

        return transferencia;
    }

    @Override
    public Optional<Transferencia> obterTransferenciaPorId(Long usuarioId) {

        return transferenciaRepository.findById(usuarioId);
    }
}