package com.picpay.project.mapper;

import com.picpay.project.dto.UsuarioDTO;
import com.picpay.project.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO usuarioParaUsuarioDto(Usuario usuario);

    Usuario usuarioDtoParaUsuario(UsuarioDTO usuarioDTO);

}
