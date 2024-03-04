package com.desafio.picpay.repository;

import com.desafio.picpay.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

     Optional<Object> findByCpf(String cnpj);
     Optional<Object> findByCnpj(String cnpj);
     Optional<Object> findByEmail(String email);
}
