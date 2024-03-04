package com.desafio.picpay.service;

import com.desafio.picpay.dto.UsuarioDTO;
import com.desafio.picpay.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario criarUsuario(UsuarioDTO usuarioDTO);

    Usuario obterUsuarioPorId(Long remetenteId);

    List<Usuario> listarUsuarios();

}
