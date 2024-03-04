package com.desafio.picpay.service.impl;

import com.desafio.picpay.dto.UsuarioDTO;
import com.desafio.picpay.entity.Usuario;
import com.desafio.picpay.excecoes.RegistroDuplicadoException;
import com.desafio.picpay.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarUsuario_Sucesso() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNomeCompleto("John Doe");
        usuarioDTO.setCpf("12345678901");
        usuarioDTO.setEmail("john.doe@example.com");

        when(usuarioRepositoryMock.findByCpf(any())).thenReturn(Optional.empty());
        when(usuarioRepositoryMock.findByEmail(any())).thenReturn(Optional.empty());

        Usuario usuarioSalvo = new Usuario();
        when(usuarioRepositoryMock.save(any())).thenAnswer(invocation -> {
            Usuario usuarioParaSalvar = invocation.getArgument(0);

            usuarioSalvo.setId(1L);
            usuarioSalvo.setNomeCompleto(usuarioParaSalvar.getNomeCompleto());
            usuarioSalvo.setCpf(usuarioParaSalvar.getCpf());
            usuarioSalvo.setEmail(usuarioParaSalvar.getEmail());
            return usuarioSalvo;
        });

    }

    @Test
    void criarUsuario_DuplicacaoDeCPF() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCpf("12345678901");

        when(usuarioRepositoryMock.findByCpf(any())).thenReturn(Optional.of(new Usuario()));

        assertThrows(RegistroDuplicadoException.class, () -> usuarioService.criarUsuario(usuarioDTO));
    }

    @Test
    void criarUsuario_DuplicacaoDeEmail() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("john.doe@example.com");

        when(usuarioRepositoryMock.findByEmail(any())).thenReturn(Optional.of(new Usuario()));

        assertThrows(RegistroDuplicadoException.class, () -> usuarioService.criarUsuario(usuarioDTO));
    }

    @Test
    void obterUsuarioPorId_Existente() {
        Long usuarioId = 1L;
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(usuarioId);

        when(usuarioRepositoryMock.findById(usuarioId)).thenReturn(Optional.of(usuarioExistente));

        Usuario usuario = usuarioService.obterUsuarioPorId(usuarioId);

        assertNotNull(usuario);
        assertEquals(usuarioId, usuario.getId());
    }

    @Test
    void obterUsuarioPorId_NaoExistente() {
        Long usuarioId = 2L;

        when(usuarioRepositoryMock.findById(usuarioId)).thenReturn(Optional.empty());

        Usuario usuario = usuarioService.obterUsuarioPorId(usuarioId);

        assertNotNull(usuario);
        assertNull(usuario.getId());
    }

    @Test
    void listarUsuarios_Sucesso() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario());
        usuarios.add(new Usuario());
        when(usuarioRepositoryMock.findAll()).thenReturn(usuarios);

        List<Usuario> listaRetornada = usuarioService.listarUsuarios();

        assertNotNull(listaRetornada);
        assertEquals(2, listaRetornada.size());
    }
}
