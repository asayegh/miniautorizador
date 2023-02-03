package com.vr.miniautorizador.controller;

import com.vr.miniautorizador.dto.TransacaoRequestDto;
import com.vr.miniautorizador.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacoes")
@Validated
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping
    public ResponseEntity<String> criarTransacao(@RequestBody @Valid TransacaoRequestDto transacaoDto) {
       return new ResponseEntity<>(service.criarTransacao(transacaoDto),
                HttpStatus.CREATED);
    }
}
