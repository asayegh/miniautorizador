package com.vr.miniautorizador.controller;

import com.vr.miniautorizador.dto.CartaoRequestDto;
import com.vr.miniautorizador.dto.CartaoResponseDto;
import com.vr.miniautorizador.service.CartaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Cartao Conroller")
@RequestMapping("/cartoes")
@Validated
public class CartaoController {

    @Autowired
    private CartaoService service;

    @ApiOperation(value = "Cria um novo cartão")
    @PostMapping
    public ResponseEntity<CartaoResponseDto> criarCartao(@RequestBody @Valid CartaoRequestDto cartaoRequestDto) {
        return new ResponseEntity<>(service.criarCartao(cartaoRequestDto),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Busca um cartão pelo número")
    @GetMapping(value = "/{numeroCartao}")
    public ResponseEntity<BigDecimal> obterCartao(@NotNull @PathVariable @Pattern(regexp = "^\\d{16}$")
                                                  String numeroCartao) {
        return new ResponseEntity<>(service.buscaCartaoPorNumero(numeroCartao), HttpStatus.OK);
    }
}
