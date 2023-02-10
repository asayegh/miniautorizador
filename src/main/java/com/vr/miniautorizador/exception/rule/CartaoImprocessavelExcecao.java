package com.vr.miniautorizador.exception.rule;

import com.vr.miniautorizador.dto.CartaoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartaoImprocessavelExcecao {

    @ExceptionHandler(CartaoImprocessavelErro.class)
    public ResponseEntity<CartaoResponseDto> handle(CartaoImprocessavelErro e) {

        var cartaoResponseDto = e.getCartaoResponseDto();
        return ResponseEntity.unprocessableEntity().body(cartaoResponseDto);

    }
}