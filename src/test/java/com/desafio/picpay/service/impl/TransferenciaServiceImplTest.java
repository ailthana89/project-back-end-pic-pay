package com.desafio.picpay.service.impl;

import com.desafio.picpay.dto.TransferenciaDTO;
import com.desafio.picpay.entity.Transferencia;
import com.desafio.picpay.entity.Usuario;
import com.desafio.picpay.excecoes.SaldoInsuficienteException;
import com.desafio.picpay.repository.TransferenciaRepository;
import com.desafio.picpay.repository.UsuarioRepository;
import com.desafio.picpay.service.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransferenciaServiceImplTest {

    private TransferenciaService transferenciaService;
    private TransferenciaRepository transferenciaRepositoryMock;
    private UsuarioRepository usuarioRepositoryMock;

    @BeforeEach
    void setUp() {
        transferenciaRepositoryMock = mock(TransferenciaRepository.class);
        usuarioRepositoryMock = mock(UsuarioRepository.class);
        transferenciaService = new TransferenciaServiceImpl(transferenciaRepositoryMock, usuarioRepositoryMock);
    }

    @Test
    void realizarTransferencia_Sucesso() {
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO(1L, 2L, BigDecimal.TEN);

        Usuario remetente = new Usuario();
        remetente.creditarCarteira(BigDecimal.TEN);
        when(usuarioRepositoryMock.findById(1L)).thenReturn(Optional.of(remetente));
        when(usuarioRepositoryMock.findById(2L)).thenReturn(Optional.of(new Usuario()));

        when(transferenciaRepositoryMock.save(any(Transferencia.class))).thenReturn(mock(Transferencia.class));

        Transferencia transferencia = transferenciaService.realizarTransferencia(transferenciaDTO);

        verify(usuarioRepositoryMock, times(2)).findById(anyLong());
        verify(transferenciaRepositoryMock, times(1)).save(any(Transferencia.class));

        assertNotNull(transferencia);
        assertEquals(transferenciaDTO.getValor(), transferencia.getValor());
    }

    @Test
    void realizarTransferencia_SaldoInsuficiente() {
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO(1L, 2L, BigDecimal.TEN);

        Usuario remetente = new Usuario();
        when(usuarioRepositoryMock.findById(1L)).thenReturn(Optional.of(remetente));
        when(usuarioRepositoryMock.findById(2L)).thenReturn(Optional.of(new Usuario()));

        assertThrows(SaldoInsuficienteException.class, () -> transferenciaService.realizarTransferencia(transferenciaDTO));

        verify(usuarioRepositoryMock, times(1)).findById(1L);
        verify(usuarioRepositoryMock, times(1)).findById(2L);
        verify(transferenciaRepositoryMock, times(0)).save(any(Transferencia.class));
    }
}
