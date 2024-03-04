package com.desafio.picpay.repository;

import com.desafio.picpay.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
     List<Transferencia> findByRemetenteIdOrDestinatarioId(Long usuarioId, Long usuarioId1);
}
