package com.vr.miniautorizador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidadorTransacao {

    @ExceptionHandler(ErroCustomizadoTransacao.class)
    public ResponseEntity<String> handle(ErroCustomizadoTransacao ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ex.getMessage());
    }
}