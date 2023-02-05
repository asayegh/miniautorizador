package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.dto.CartaoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomValidationExceptionAdvice {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<CartaoResponseDto> handle(CustomValidationException ex) {

        var cartaoResponseDto = ex.getCartaoResponseDto();
        return ResponseEntity.unprocessableEntity().body(cartaoResponseDto);

    }
}