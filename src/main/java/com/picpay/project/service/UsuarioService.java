package com.picpay.project.service;

import com.picpay.project.dto.UsuarioDTO;
import com.picpay.project.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO criarUsuario(UsuarioDTO dto);

    void validaDocumento(UsuarioDTO dto);

    List<Usuario> listarUsuarios();

    Usuario buscarUsuarioPorId(Long id);

    Usuario alterarUsuario(Long id, UsuarioDTO usuarioDTO);

    void deletarUsuario(Long id);
}
