package com.vr.miniautorizador.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vr.miniautorizador.dto.CartaoDto;
import com.vr.miniautorizador.jackson.Views;
import com.vr.miniautorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@RestController
@RequestMapping("/cartoes")
@Validated
public class CartaoController {

    @Autowired
    private CartaoService service;

    @PostMapping
    @JsonView(Views.Create.class)
    public ResponseEntity<CartaoDto> criarCartao(@RequestBody @Valid CartaoDto cartaoDto) {
        return new ResponseEntity<>(service.criarCartao(cartaoDto),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/{numeroCartao}")
    @JsonView(Views.Create.class)
    public ResponseEntity<BigDecimal> obterCartao(@NotNull @PathVariable @Pattern(regexp = "^\\d{16}$")
                                                  String numeroCartao) {
        return new ResponseEntity<>(service.buscaCartaoPorNumero(numeroCartao), HttpStatus.OK);
    }
}
