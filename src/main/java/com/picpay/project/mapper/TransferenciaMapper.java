package com.picpay.project.mapper;

import com.picpay.project.dto.TransferenciaDTO;
import com.picpay.project.entity.Transferencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {

    @Mapping(source = "remetente.id", target = "remetente")
    @Mapping(source = "destinatario.id", target = "destinatario")
    TransferenciaDTO transferenciaToTransferenciaDTO(Transferencia transferencia);

    @Mapping(source = "remetente", target = "remetente.id")
    @Mapping(source = "destinatario", target = "destinatario.id")
    Transferencia transferenciaDTOToTransferencia(TransferenciaDTO transferenciaDTO);
}
