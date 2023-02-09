package com.vr.miniautorizador.exception.validation;

import com.vr.miniautorizador.dto.CartaoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidacaoCriarCartaoErroImprocessavelExcecao {

    @ExceptionHandler(ValidacaoCriarCartaoErroImprocessavel.class)
    public ResponseEntity<CartaoResponseDto> handle(ValidacaoCriarCartaoErroImprocessavel ex) {

        var cartaoResponseDto = ex.getCartaoResponseDto();
        return ResponseEntity.unprocessableEntity().body(cartaoResponseDto);

    }
}