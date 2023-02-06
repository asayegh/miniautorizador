package com.vr.miniautorizador.controller;

import com.vr.miniautorizador.dto.TransacaoRequestDto;
import com.vr.miniautorizador.service.TransacaoService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Executa uma transação financeira")
    @PostMapping
    public ResponseEntity<String> criarTransacao(@RequestBody @Valid TransacaoRequestDto transacaoRequestDto) {
       return new ResponseEntity<>(service.criarTransacao(transacaoRequestDto),
                HttpStatus.CREATED);
    }
}
