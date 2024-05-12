package com.picpay.project.controlller;

import com.picpay.project.dto.UsuarioDTO;
import com.picpay.project.entity.Usuario;
import com.picpay.project.excecoes.UsuarioNotFoundException;
import com.picpay.project.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    public static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioDTO usuarioDto) {
        try {
            UsuarioDTO novoUsuario = usuarioService.criarUsuario(usuarioDto);
            logger.info("Usuário criado com sucesso!");
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        } catch (UsuarioNotFoundException ex) {
            logger.error("Erro ao criar usuário: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar usuário: " + ex.getMessage());
        }
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<?>> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            logger.info("Listagem de usuários com sucesso!");
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (UsuarioNotFoundException ex) {
            logger.error("Erro ao criar usuário: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList("Erro ao criar usuário: " + ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(id);
            logger.info("Usuário encontrado com sucesso!");
            return ResponseEntity.ok().body(usuario);
        } catch (UsuarioNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar usuário por ID: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> alterarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioService.alterarUsuario(id, usuarioDTO);
            logger.info("Usuário alterado com sucesso!");
            return ResponseEntity.ok().body(usuario);
        } catch (UsuarioNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao alterar usuário: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
            logger.info("Usuário deletado com sucesso!");
            return ResponseEntity.noContent().build();
        } catch (UsuarioNotFoundException ex) {
            logger.error("Erro ao alterar o usuário: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar usuário: " + ex.getMessage());
        }
    }
}

