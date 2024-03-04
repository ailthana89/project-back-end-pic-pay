package com.desafio.picpay.controller;

import com.desafio.picpay.dto.TransferenciaDTO;
import com.desafio.picpay.entity.Transferencia;
import com.desafio.picpay.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    public ResponseEntity<Transferencia> realizarTransferencia(@RequestBody TransferenciaDTO transferenciaDTO) {
        Transferencia transferenciaRealizada = transferenciaService.realizarTransferencia(transferenciaDTO);
        return new ResponseEntity<>(transferenciaRealizada, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Transferencia>> obterTransferenciasPorUsuario(@PathVariable Long usuarioId) {
        List<Transferencia> transferencias = transferenciaService.obterTransferenciasPorUsuario(usuarioId);
        return new ResponseEntity<>(transferencias, HttpStatus.OK);
    }

}
