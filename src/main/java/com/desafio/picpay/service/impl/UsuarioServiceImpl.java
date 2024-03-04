package com.desafio.picpay.service.impl;

import com.desafio.picpay.dto.UsuarioDTO;
import com.desafio.picpay.entity.Usuario;
import com.desafio.picpay.excecoes.RegistroDuplicadoException;
import com.desafio.picpay.excecoes.UsuarioNullPointerException;
import com.desafio.picpay.repository.UsuarioRepository;
import com.desafio.picpay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario criarUsuario(UsuarioDTO usuarioDTO) {

        if(validarCpfCnpjEEmail(usuarioDTO.getCpf(), usuarioDTO.getCnpj(), usuarioDTO.getEmail())) {

            Usuario usuario = new Usuario();
            usuario.setNomeCompleto(usuarioDTO.getNomeCompleto());
            usuario.setCpf(usuarioDTO.getCpf());
            usuario.setCnpj(usuarioDTO.getCnpj());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setSenha(usuarioDTO.getSenha());
            usuario.setCarteira(usuarioDTO.getCarteira());

            return usuarioRepository.save(usuario);

        } else {
            throw new UsuarioNullPointerException("Usuário não encontrado");
        }

    }

    @Override
    public Usuario obterUsuarioPorId(Long remetenteId) {
        Optional<Usuario> usuario = usuarioRepository.findById(remetenteId);

        return usuario.orElseGet(Usuario::new);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    private boolean validarCpfCnpjEEmail(String cpf, String cnpj, String email) {
        if (usuarioRepository.findByCpf(cpf).isPresent()) {
            throw new RegistroDuplicadoException("CPF já está em uso por outro usuário.");
        }

        if (usuarioRepository.findByCnpj(cnpj).isPresent()) {
            throw new RegistroDuplicadoException("CNPJ já está em uso por outro usuário.");
        }

        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new RegistroDuplicadoException("E-mail já está em uso por outro usuário.");
        }
        return true;
    }

}
