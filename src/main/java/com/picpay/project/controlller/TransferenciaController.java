package com.picpay.project.controlller;

import com.picpay.project.dto.TransferenciaDTO;
import com.picpay.project.entity.Transferencia;
import com.picpay.project.entity.Usuario;
import com.picpay.project.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/{transferenciaId}")
    public ResponseEntity<Optional<Transferencia>> obterTransferenciasPorId(@PathVariable Long transferenciaId) {
        Optional<Transferencia> transferencias = transferenciaService.obterTransferenciaPorId(transferenciaId);
        return new ResponseEntity<>(transferencias, HttpStatus.OK);
    }

}
