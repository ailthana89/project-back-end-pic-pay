package com.picpay.project.service.impl;

import com.picpay.project.dto.UsuarioDTO;
import com.picpay.project.entity.Usuario;
import com.picpay.project.excecoes.UsuarioNotFoundException;
import com.picpay.project.mapper.UsuarioMapper;
import com.picpay.project.repository.UsuarioRepository;
import com.picpay.project.service.UsuarioService;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static com.picpay.project.entity.TipoUsuario.LOJISTA;
import static com.picpay.project.entity.TipoUsuario.USUARIO_COMUM;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final  UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        Usuario usuario = usuarioMapper.usuarioDtoParaUsuario(dto);
        //validaDocumento(dto);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioParaUsuarioDto(usuarioSalvo);
    }

    @Override
    public void validaDocumento(UsuarioDTO dto) {
        CPFValidator cpfValidator = new CPFValidator();
        CNPJValidator cnpjValidator = new CNPJValidator();
        String documento = String.valueOf(dto.getDocumento());

        if (documento != null) {
            if (cpfValidator.isValid(documento, null)) {
                dto.setTipoUsuario(USUARIO_COMUM);
            } else if (cnpjValidator.isValid(documento, null)) {
                dto.setTipoUsuario(LOJISTA);
            }
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(@RequestBody Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()) {
           throw new UsuarioNotFoundException("Usuário não encontrado com o ID: " + id);
        }
        return usuario.get();
    }

    @Override
    public Usuario alterarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado com o ID: " + id));
        usuario.setNomeCompleto( usuarioDTO.getNomeCompleto() );
        usuario.setDocumento( usuarioDTO.getDocumento() );
        usuario.setSenha( usuarioDTO.getSenha() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setTipoUsuario( usuarioDTO.getTipoUsuario() );

        return usuarioRepository.save(usuario);
    }

    @Override
    public void deletarUsuario(Long id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }
}
