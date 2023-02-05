package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.dto.CartaoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidacaoCriarCartaoExcecao {

    @ExceptionHandler(ValidacaoCriarCartao.class)
    public ResponseEntity<CartaoResponseDto> handle(ValidacaoCriarCartao ex) {

        var cartaoResponseDto = ex.getCartaoResponseDto();
        return ResponseEntity.unprocessableEntity().body(cartaoResponseDto);

    }
}